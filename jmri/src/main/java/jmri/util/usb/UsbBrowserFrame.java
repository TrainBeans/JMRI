// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.util.usb;

import jmri.util.JmriJFrame;
import javax.swing.JPanel; 

/**
 *
 * @author Randall Wood
 */
public class UsbBrowserFrame extends JmriJFrame {

    public UsbBrowserFrame() {
        this(new UsbBrowserPanel());
    }

    UsbBrowserFrame(JPanel rootPanel) {
        super(Bundle.getMessage("UsbBrowserFrame.Title"));
        super.getRootPane().setContentPane(rootPanel);
        super.pack();
    }
}
