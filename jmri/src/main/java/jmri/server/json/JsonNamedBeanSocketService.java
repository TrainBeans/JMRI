// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.server.json;

import static jmri.server.json.JSON.DATA;
import static jmri.server.json.JSON.DELETE;
import static jmri.server.json.JSON.GET;
import static jmri.server.json.JSON.NAME;
import static jmri.server.json.JSON.POST;
import static jmri.server.json.JSON.PUT;

import com.fasterxml.jackson.databind.JsonNode;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import jmri.InstanceManager;
import jmri.JmriException;
import jmri.NamedBean;
import jmri.ReporterManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract implementation of JsonSocketService with specific support for
 * {@link jmri.NamedBean} objects. Note that services requiring support for
 * multiple classes of NamedBean cannot extend this class.
 *
 * @author Randall Wood (C) 2019
 * @param <T> the NamedBean class supported by this service
 * @param <H> the supporting JsonNamedBeanHttpService class
 */
public class JsonNamedBeanSocketService<T extends NamedBean, H extends JsonNamedBeanHttpService<T>> extends JsonSocketService<H> {

    protected final HashMap<T, NamedBeanListener> beanListeners = new HashMap<>();
    protected final ManagerListener managerListener = new ManagerListener();
    private static final Logger log = LoggerFactory.getLogger(JsonNamedBeanSocketService.class);

    public JsonNamedBeanSocketService(JsonConnection connection, H service) {
        super(connection, service);
        service.getManager().addPropertyChangeListener(managerListener);
    }

    @Override
    public void onMessage(String type, JsonNode data, JsonRequest request)
            throws IOException, JmriException, JsonException {
        String name = data.path(NAME).asText();
        T bean = null;
        // protect against a request made with a user name instead of a system name
        if (!request.method.equals(PUT)) {
            bean = service.getManager().getBySystemName(name);
            if (bean == null) {
                bean = service.getManager().getByUserName(name);
                if (bean != null) {
                    // set to warn so users can provide specific feedback to developers of JSON clients
                    log.warn("{} request for {} made with user name \"{}\"; should use system name", request.method, type, name);
                    name = bean.getSystemName();
                } // service will throw appropriate error to client later if bean is still null
            }
        }
        switch (request.method) {
            case DELETE:
                service.doDelete(type, name, data, request);
                break;
            case POST:
                connection.sendMessage(service.doPost(type, name, data, request), request.id);
                break;
            case PUT:
                JsonNode message = service.doPut(type, name, data, request);
                connection.sendMessage(message, request.id);
                bean = service.getManager().getBySystemName(message.path(DATA).path(NAME).asText());
                break;
            case GET:
            default:
                connection.sendMessage(service.doGet(type, name, data, request), request.id);
        }
        if (!beanListeners.containsKey(bean)) {
            addListenerToBean(bean);
        }
    }

    @Override
    public void onList(String type, JsonNode data, JsonRequest request) throws IOException, JmriException, JsonException {
        connection.sendMessage(service.doGetList(type, data, request), request.id);
    }

    @Override
    public void onClose() {
        beanListeners.values().stream().forEach(listener -> listener.bean.removePropertyChangeListener(listener));
        beanListeners.clear();
        service.getManager().removePropertyChangeListener(managerListener);
    }

    protected void addListenerToBean(String name) {
        addListenerToBean(service.getManager().getBySystemName(name));
    }

    protected void addListenerToBean(T bean) {
        if (bean != null) {
            NamedBeanListener listener = new NamedBeanListener(bean);
            bean.addPropertyChangeListener(listener);
            this.beanListeners.put(bean, listener);
        }
    }

    protected void removeListenersFromRemovedBeans() {
        for (T bean : new HashSet<>(beanListeners.keySet())) {
            if (service.getManager().getBySystemName(bean.getSystemName()) == null) {
                beanListeners.remove(bean);
            }
        }
    }

    protected class NamedBeanListener implements PropertyChangeListener {

        public final T bean;

        public NamedBeanListener(T bean) {
            this.bean = bean;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            try {
                connection.sendMessage(service.doGet(this.bean, this.bean.getSystemName(), service.getType(), new JsonRequest(getLocale(), getVersion(), JSON.GET, 0)), 0);
            } catch (
                    IOException |
                    JsonException ex) {
                // if we get an error, unregister as listener
                this.bean.removePropertyChangeListener(this);
                beanListeners.remove(this.bean);
            }
        }
    }

    protected class ManagerListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            try {
                handleChange(evt);
            } catch (IOException ex) {
                // if we get an error, unregister as listener
                log.debug("deregistering reportersListener due to IOException");
                InstanceManager.getDefault(ReporterManager.class).removePropertyChangeListener(this);
            }
        }

        private void handleChange(PropertyChangeEvent evt) throws IOException {
            try {
                // send the new list
                connection.sendMessage(service.doGetList(service.getType(),
                        service.getObjectMapper().createObjectNode(), new JsonRequest(getLocale(), getVersion(), JSON.GET, 0)), 0);
                //child added or removed, reset listeners
                if (evt.getPropertyName().equals("length")) { // NOI18N
                    removeListenersFromRemovedBeans();
                }
            } catch (JsonException ex) {
                log.warn("json error sending {}: {}", service.getType(), ex.getJsonMessage());
                connection.sendMessage(ex.getJsonMessage(), 0);
            }
        }
    }
}
