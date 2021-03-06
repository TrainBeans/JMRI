// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.operations.rollingstock.engines.tools;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Starts the ImportEngines thread
 *
 * @author Dan Boudreau Copyright (C) 2008
 */
public class ExportEngineRosterAction extends AbstractAction {

    public ExportEngineRosterAction() {
        super(Bundle.getMessage("MenuItemExport"));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ExportEngines ex = new ExportEngines();
        ex.writeOperationsEngineFile();
    }

//    private final static Logger log = LoggerFactory.getLogger(ExportEngineRosterAction.class);
}
