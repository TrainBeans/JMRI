// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.server.json.turnout;

import jmri.Turnout;
import jmri.server.json.JsonConnection;
import jmri.server.json.JsonNamedBeanSocketService;

/**
 *
 * @author Randall Wood
 */
public class JsonTurnoutSocketService extends JsonNamedBeanSocketService<Turnout, JsonTurnoutHttpService> {

    public JsonTurnoutSocketService(JsonConnection connection) {
        super(connection, new JsonTurnoutHttpService(connection.getObjectMapper()));
    }
}
