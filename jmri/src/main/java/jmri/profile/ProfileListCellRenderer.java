// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.profile;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * Implements a list cell renderer that provides a Profile-specific tool tip.
 *
 * @author Randall Wood (C) 2016
 */
public class ProfileListCellRenderer extends DefaultListCellRenderer {

    public ProfileListCellRenderer() {
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (-1 < index && null != value && value instanceof Profile) {
                Profile profile = (Profile) value;
            list.setToolTipText(Bundle.getMessage("ProfileTableModel.toolTip", profile.getName(), profile.getPath(), profile.getId(), "")); // NOI18N
        } else {
            list.setToolTipText(Bundle.getMessage("ProfileManagerDialog.profiles.toolTipText"));
        }
        return comp;
    }

}
