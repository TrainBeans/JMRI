// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.automat;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Swing action to create and register a SampleAutomaton2 object
 *
 * @author Bob Jacobsen Copyright (C) 2003
 */
public class SampleAutomaton2Action extends AbstractAction {

    public SampleAutomaton2Action(String s) {
        super(s);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // create a SampleAutomaton2
        AbstractAutomaton f = new SampleAutomaton2();
        f.start();
    }
}
