// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.rfid.merg.concentrator.configurexml;

import org.jdom2.Element;

/**
 * Provides load and store functionality for configuring ConcentratorSensorManagers.
 * <p>
 * Uses the store method from the abstract base class, but provides a load
 * method here.
 *
 * @author Bob Jacobsen Copyright: Copyright (c) 2008
 * @author Matthew Harris Copyright (C) 2011
 * @since 2.11.4
 */
public class ConcentratorSensorManagerXml extends jmri.jmrix.rfid.configurexml.RfidSensorManagerXml {

    public ConcentratorSensorManagerXml() {
        super();
    }

    @Override
    public void setStoreElementClass(Element sensors) {
        sensors.setAttribute("class", this.getClass().getName());
    }
}
