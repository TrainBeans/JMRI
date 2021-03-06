// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.operations.locations.tools;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jmri.jmrit.operations.setup.Setup;

/**
 * Track tool to enable the display of track moves.
 *
 * @author Bob Jacobsen Copyright (C) 2001
 * @author Daniel Boudreau Copyright (C) 2014
 */
public class ShowTrackMovesAction extends AbstractAction {

    public ShowTrackMovesAction() {
        super(Bundle.getMessage("MenuItemShowTrackMoves"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // toggle
        Setup.setShowTrackMovesEnabled(!Setup.isShowTrackMovesEnabled());
    }
}


