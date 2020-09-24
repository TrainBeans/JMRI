// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.util;

import jmri.util.swing.NamedIcon;

public class SmallPowerManagerButton extends PowerManagerButton {

    public SmallPowerManagerButton(Boolean fullText) {
        super(fullText);
    }

    public SmallPowerManagerButton() {
        super();
    }
    
    @Override
    protected void initComponents() {
        setBorderPainted(false);
    }

    @Override
    protected void loadIcons() {
        setPowerOnIcon(new NamedIcon("resources/icons/throttles/GreenPowerLED.gif", "resources/icons/throttles/GreenPowerLED.gif"));
        setPowerOffIcon(new NamedIcon("resources/icons/throttles/RedPowerLED.gif", "resources/icons/throttles/RedPowerLED.gif"));
        setPowerUnknownIcon(new NamedIcon("resources/icons/throttles/YellowPowerLED.gif", "resources/icons/throttles/YellowPowerLED.gif"));
    }

}
