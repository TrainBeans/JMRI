// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.util;

import jmri.util.swing.NamedIcon;

public class LargePowerManagerButton extends PowerManagerButton {

    public LargePowerManagerButton(Boolean fullText) {
        super(fullText);
    }

    public LargePowerManagerButton() {
        super();
    }

    @Override
    protected void loadIcons() {
        setPowerOnIcon(new NamedIcon("resources/icons/throttles/power_green.png", "resources/icons/throttles/power_green.png"));
        setPowerOffIcon(new NamedIcon("resources/icons/throttles/power_red.png", "resources/icons/throttles/power_red.png"));
        setPowerUnknownIcon(new NamedIcon("resources/icons/throttles/power_yellow.png", "resources/icons/throttles/power_yellow.png"));
    }

}
