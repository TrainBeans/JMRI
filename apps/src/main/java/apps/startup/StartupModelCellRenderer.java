// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package apps.startup;

import jmri.util.startup.StartupModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Render a {@link StartupModel} in a table.
 *
 * @author Randall Wood Copyright (C) 2015, 2016
 */
class StartupModelCellRenderer extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        // null anything is rendered as an empty cell.
        if (value != null) {
            String text = ((StartupModel) value).getName();
            setText(text != null ? text : value.toString()); // NOI18N
        } else {
            setText(""); // NOI18N
        }
    }
}
