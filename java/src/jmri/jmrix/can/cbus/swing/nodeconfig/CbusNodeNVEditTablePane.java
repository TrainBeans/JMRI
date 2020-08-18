// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.can.cbus.swing.nodeconfig;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import jmri.jmrix.can.cbus.node.CbusNode;
import jmri.jmrix.can.cbus.node.CbusNodeNVTableDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pane providing a Cbus event table. Menu code copied from BeanTableFrame.
 *
 * @author Steve Young (C) 2019
 *
 * @since 4.15.5
 */
public class CbusNodeNVEditTablePane extends jmri.jmrix.can.swing.CanPanel {

    private final CbusNodeNVTableDataModel nodeNVModel;
    private int largerFont;
    private final JTable nodeNvTable;
    private JScrollPane eventVarScroll;
    private JPanel pane1;

    protected CbusNodeNVEditTablePane( CbusNodeNVTableDataModel nVModel ) {
        super();
        nodeNVModel = nVModel;
        nodeNvTable = new JTable(nodeNVModel);
        
    }
    
    protected void setNode(CbusNode node ) {
        
        if (pane1==null){
            initTable();
        }
        
        CbusNode nodeOfInterest = node;
        nodeNVModel.setNode( nodeOfInterest );
        pane1.setVisible(!(node == null));
    }
    
    private void initTable(){
        
        pane1 = new JPanel();
    
        TableColumnModel tableModel = nodeNvTable.getColumnModel();
        
        nodeNvTable.createDefaultColumnsFromModel();
        
        nodeNvTable.setRowSelectionAllowed(true);
        nodeNvTable.setColumnSelectionAllowed(false);
        nodeNvTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        nodeNvTable.getTableHeader().setReorderingAllowed(true);
        nodeNvTable.setRowHeight(27);
        
        tableModel.getColumn(CbusNodeNVTableDataModel.NV_NUMBER_COLUMN).setCellRenderer(getRenderer());
        tableModel.getColumn(CbusNodeNVTableDataModel.NV_CURRENT_VAL_COLUMN).setCellRenderer( getRenderer() );
        tableModel.getColumn(CbusNodeNVTableDataModel.NV_CURRENT_HEX_COLUMN).setCellRenderer(getRenderer());
        tableModel.getColumn(CbusNodeNVTableDataModel.NV_CURRENT_BIT_COLUMN).setCellRenderer( getRenderer() );        
        
        log.debug("_editable : {} , tot column {}",_editable, nodeNVModel.getColumnCount());
        
        if (_editable) {
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_COLUMN).setCellRenderer( new SpinnerRenderer() );
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_COLUMN).setCellEditor( new NvSpinnerEditor() );
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_HEX_COLUMN).setCellRenderer(getRenderer());
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_BIT_COLUMN).setCellRenderer( getRenderer() );
        }
        else {
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_COLUMN).setMinWidth(0);
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_COLUMN).setMaxWidth(0);
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_COLUMN).setWidth(0);
            
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_HEX_COLUMN).setMinWidth(0);
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_HEX_COLUMN).setMaxWidth(0);
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_HEX_COLUMN).setWidth(0);
            
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_BIT_COLUMN).setMinWidth(0);
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_BIT_COLUMN).setMaxWidth(0);
            tableModel.getColumn(CbusNodeNVTableDataModel.NV_SELECT_BIT_COLUMN).setWidth(0);
        }
        
        largerFont = new JTextField().getFont().getSize()+2;
        
        setLayout(new BorderLayout() );
        
        pane1.setLayout(new BorderLayout());
        
        // scroller for main table
        eventVarScroll = new JScrollPane(nodeNvTable);

        pane1.add(eventVarScroll);
        
        add(pane1);
        pane1.setVisible(true);
        
        nodeNvTable.setAutoCreateColumnsFromModel( false );
        
        add(eventVarScroll);
    
    }
    
    private boolean _editable = true;
    
    protected void setNonEditable() {
        _editable = false;
    }
    
    public static final Color WHITE_GREEN = new Color(0xf5,0xf5,0xf5);
    
    /**
     * Cell Renderer for string table columns, highlights any text in filter input
     */    
    private TableCellRenderer getRenderer() {
        return new TableCellRenderer() {
            
            JTextField f = new JTextField();
            
            @Override
            public Component getTableCellRendererComponent(
                JTable table, Object arg1, boolean isSelected, boolean hasFocus, int row, int col) {
                
                f.setHorizontalAlignment(JTextField.CENTER);
                f.setBorder( table.getBorder() );
                
                int tablecol = nodeNvTable.convertColumnIndexToModel(col);

                if ( tablecol == CbusNodeNVTableDataModel.NV_NUMBER_COLUMN ){
                    f.setFont(f.getFont().deriveFont(Font.BOLD, largerFont ));
                }
                
                int oldval = (int) nodeNVModel.getValueAt(row, CbusNodeNVTableDataModel.NV_CURRENT_VAL_COLUMN);
                int newval = (int) nodeNVModel.getValueAt(row, CbusNodeNVTableDataModel.NV_SELECT_COLUMN);
                
                String string;
                if(arg1 != null){
                    string = arg1.toString();
                    try {
                        if (Integer.parseInt(string)<0){
                            string="";
                        }
                    } catch (NumberFormatException ex) {
                    }
                    
                    f.setText(string.toUpperCase());
                } else {
                    f.setText("");
                }
                
                if (( tablecol == CbusNodeNVTableDataModel.NV_SELECT_HEX_COLUMN
                    || tablecol == CbusNodeNVTableDataModel.NV_SELECT_BIT_COLUMN)
                    && ( oldval == newval ) ){
                    f.setText("");
                }
                
                
                if (isSelected) {
                    if ( oldval != newval ) {
                        f.setBackground( Color.orange );
                    }
                    else {
                        f.setBackground( table.getSelectionBackground() );
                    }
                } else {
                    if ( oldval != newval ) {
                        f.setBackground( Color.yellow );
                    }
                    else {
                        if ( row % 2 == 0 ) {
                            f.setBackground( table.getBackground() );
                        }
                        else {
                            f.setBackground( WHITE_GREEN );
                        }
                    }
                }
                return f;
            }
        };
    }

    protected static class NvSpinnerEditor extends AbstractCellEditor implements ChangeListener, TableCellEditor {
        
        final JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));

        public NvSpinnerEditor() {
            init();
        }
        
        final void init() {
            spinner.addChangeListener(this);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {
            spinner.setValue( value);
            spinner.setBorder(null);
            
            return spinner;
        }

        @Override
        public boolean isCellEditable(EventObject evt) {
            if (evt instanceof MouseEvent) {
                return ((MouseEvent) evt).getClickCount() >= 1;
            }
            return true;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
        
        @Override
        public void stateChanged(ChangeEvent eve) {
            stopCellEditing();
        }
    }

    protected static class SpinnerRenderer extends JSpinner implements TableCellRenderer {
        
        public SpinnerRenderer() {
        }
   
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            setModel(new SpinnerNumberModel( (int) value, -1, 255, 1));
            setEnabled((int)value >-1);
            this.setOpaque(true);
            this.setBorder(null);
            return this;
        }
    }

    private final static Logger log = LoggerFactory.getLogger(CbusNodeNVEditTablePane.class);

}
