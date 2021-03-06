// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.cmri.serial;

import jmri.jmrix.cmri.CMRISystemConnectionMemo;

/*
 * Identifying class representing a network communications port
 * @author   Bob Jacobsen    Copyright (C) 2001, 2015
 */
public abstract class SerialNetworkPortAdapter extends jmri.jmrix.AbstractNetworkPortController {

    protected SerialNetworkPortAdapter(CMRISystemConnectionMemo connectionMemo) {
        super(connectionMemo);
    }

    @Override
    public CMRISystemConnectionMemo getSystemConnectionMemo() {
        return (CMRISystemConnectionMemo) super.getSystemConnectionMemo();
    }

}
