// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.loconet.hexfile;

/**
 * Lightweight class to denote that a system is "active" via a LocoNet hexfile emulator.
 * <p>
 * Objects of specific subtypes are registered in the instance manager to
 * activate their particular system.
 *
 * @author Kevin Dickerson Copyright (C) 2010
 */
public class HexFileSystemConnectionMemo extends jmri.jmrix.loconet.LocoNetSystemConnectionMemo {

/*    @Override
    public jmri.jmrix.loconet.LnSensorManager getSensorManager() {
        if (getDisabled()) {
            return null;
        }
        if (sensorManager == null) {
            sensorManager = new LnSensorManager(this);
        }
        return sensorManager;
    }*/

}
