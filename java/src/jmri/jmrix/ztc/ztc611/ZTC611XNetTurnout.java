// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.ztc.ztc611;

import jmri.jmrix.lenz.XNetTrafficController;

/**
 * Extend jmri.jmrix.XNetTurnout to handle turnouts on ZTC ZTC611
 * connections. See XNetTurnout for further documentation.
 *
 * @author Paul Bender Copyright (C) 2008,2017
 */
public class ZTC611XNetTurnout extends jmri.jmrix.lenz.XNetTurnout {

    public ZTC611XNetTurnout(String prefix, int pNumber, XNetTrafficController tc) {  // a human-readable turnout number must be specified!
        super(prefix, pNumber, tc);
    }

    /* Send an "Off" message to the decoder for this output  */
    @Override
    protected synchronized void sendOffMessage() {
        // The ZTC611 appears to react send an on DCC packet when the On 
        // command is sent and an off DCC packet when the OFF command is
        // sent.  This causes some decoders to turn off the output 
        // instead of waiting for the time to expire.
        // so set the known state to the commanded state, and send
        // the next queued message.
        newKnownState(getCommandedState());
        sendQueuedMessage();
    }

}
