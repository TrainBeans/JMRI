// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.operations.trains.tools;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Action to create the train lineups in a CSV file.
 *
 * @author Dan Boudreau Copyright (C) 2020
 * 
 */
public class ExportTrainLineupsAction extends AbstractAction {

    public ExportTrainLineupsAction() {
        super(Bundle.getMessage("MenuItemExportLineup"));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new ExportTrainLineups().writeOperationsTrainsFile();
    }
}
