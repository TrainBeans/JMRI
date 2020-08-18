// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.marklin;

/*
 * Identifying class representing a Marklin communications port
 *
 * @author   Kevin Dickerson    Copyright (C) 2001, 2008
 */
public abstract class MarklinPortController extends jmri.jmrix.AbstractNetworkPortController {

    /**
     * Base class. Implementations will provide InputStream and OutputStream
     * objects to MarklinTrafficController classes, who in turn will deal in messages.
     *
     * @param connectionMemo for access to connection objects
     */
    protected MarklinPortController(MarklinSystemConnectionMemo connectionMemo) {
        super(connectionMemo);
    }

    @Override
    public MarklinSystemConnectionMemo getSystemConnectionMemo() {
        return (MarklinSystemConnectionMemo) super.getSystemConnectionMemo();
    }

}
