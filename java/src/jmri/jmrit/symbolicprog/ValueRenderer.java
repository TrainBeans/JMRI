// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.symbolicprog;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * ValueRenderer.java
 *
 * Renders enum table cells
 *
 * @author Bob Jacobsen Copyright (C) 2001
 */
public class ValueRenderer implements TableCellRenderer {

    public ValueRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        // if (log.isDebugEnabled()) log.debug("getTableCellRendererComponent "
        //       +" "+row+" "+column
        //       +" "+isSelected+" "+hasFocus
        //       +" "+value);
        if (value instanceof Component) {
            return (Component) value;
        } else if (value instanceof String) {
            return new JLabel((String) value);
        } else {
            return new JLabel("Unknown value type!");
        }
    }
}
