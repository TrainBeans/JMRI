// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.operations.trains.excel;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Swing action to launch the SetupExcelProgramFrame.
 *
 * @author Daniel Boudreau Copyright (C) 2013
 * 
 */
public class SetupExcelProgramFrameAction extends AbstractAction {

    public SetupExcelProgramFrameAction() {
        super(Bundle.getMessage("MenuItemSetupExcelProgram"));
    }

    SetupExcelProgramManifestFrame f = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        // create a train scripts frame
        if (f != null && f.isVisible()) {
            f.dispose();
        }
        f = new SetupExcelProgramManifestFrame();
        f.initComponents();
        f.setExtendedState(Frame.NORMAL);
    }
}


