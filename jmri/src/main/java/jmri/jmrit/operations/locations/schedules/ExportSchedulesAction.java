// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.operations.locations.schedules;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Starts the export schedules action
 *
 * @author Dan Boudreau Copyright (C) 2018
 */
public class ExportSchedulesAction extends AbstractAction {

    public ExportSchedulesAction() {
        super(Bundle.getMessage("MenuItemExportSchedules"));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new ExportSchedules().writeOperationsScheduleFile();
    }
}
