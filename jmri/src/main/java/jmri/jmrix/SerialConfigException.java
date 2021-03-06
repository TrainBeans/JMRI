// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix;

/**
 * Represents a failure during the configuration of a serial port, typically via
 * a SerialPortAdapter interface.
 *
 * @author Bob Jacobsen Copyright (C) 2001
 */
public class SerialConfigException extends jmri.JmriException {

    public SerialConfigException(String s) {
        super(s);
    }

    public SerialConfigException() {
    }

}
