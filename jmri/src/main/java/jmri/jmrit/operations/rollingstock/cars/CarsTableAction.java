// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.operations.rollingstock.cars;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Swing action to create and register a CarsTableFrame object.
 *
 * @author Bob Jacobsen Copyright (C) 2001
 * @author Daniel Boudreau Copyright (C) 2008
 */
public class CarsTableAction extends AbstractAction {

    public CarsTableAction() {
        super(Bundle.getMessage("MenuCars")); // NOI18N
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // create a car table frame
        new CarsTableFrame(true, null, null);
    }
}


