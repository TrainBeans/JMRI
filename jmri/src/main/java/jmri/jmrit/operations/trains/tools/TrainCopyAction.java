// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.operations.trains.tools;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jmri.jmrit.operations.trains.Train;

/**
 * Swing action to create and register a TrainCopyFrame object.
 *
 * @author Bob Jacobsen Copyright (C) 2001
 * @author Daniel Boudreau Copyright (C) 2011
 */
public class TrainCopyAction extends AbstractAction {

    public TrainCopyAction() {
        super(Bundle.getMessage("TitleTrainCopy"));
    }

    Train _train = null;

    public TrainCopyAction(Train train) {
        super(Bundle.getMessage("TitleTrainCopy"));
        _train = train;
        setEnabled(train != null);
    }

    TrainCopyFrame f = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        // create a copy train frame
        if (f == null || !f.isVisible()) {
            f = new TrainCopyFrame(_train);
        }
        f.setExtendedState(Frame.NORMAL);
        f.setVisible(true); // this also brings the frame into focus
    }
}


