/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.view.table.renderer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author 35191
 */
public class MyTableModelBase extends AbstractTableModel{

    

        private final  String[] columnNames;      
        private final ArrayList<JComponent[]> objects = new ArrayList<>();
        
        public MyTableModelBase(String[] columnNames){
          this.columnNames = columnNames;
          //addRow(new JLabel("A"),new JLabel("B"));         
    
          
        }

        public void addRow(JComponent... component){
          //for(JComponent c : component){c.setFocusable(false);}
          objects.add(component);
        }

          public TableCellRenderer getTableCellRenderer(){
          return new TableCellRendererBase();
          }
          
           public TableCellEditor getTableCellEditorBase(){
             return new TableCellEditorBase();
          }
          
           
           public void clean(){
//               objects.forEach((components) -> {
//                   for (JComponent component : components) {
//                       component.setVisible(false);
//                   }
//            });
               objects.clear();
           }
       @Override
        public int getColumnCount() {
            return columnNames.length;
        }

       @Override
        public int getRowCount() {
            return objects.size();
        }

       @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

       @Override
        public Object getValueAt(int row, int col) {         
            return   objects.get(row)[col] instanceof JComponent ? (JComponent) objects.get(row)[col] : objects.get(row)[col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
       @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

       @Override
        public boolean isCellEditable(int row, int col) {          
           return col >= 0;
        }

       @Override
        public void setValueAt(Object value, int row, int col) {
            if(row >= 0 && objects.size() > 0 && row <= objects.size() && col >= 0 && objects.get(0).length > 0 && col <= objects.get(0).length ){
            objects.get(row)[col] = (JComponent) value;
            //data[row][col] = value;
            fireTableCellUpdated(row, col);
     }
            
        }
    
        protected class TableCellRendererBase extends JComponent implements TableCellRenderer{

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {                
                return value instanceof JComponent ? (JComponent) value : this;
            }
    
        }

        protected class TableCellEditorBase extends AbstractCellEditor implements TableCellEditor , ActionListener{

                    public JComponent component;

                    public TableCellEditorBase(){

                    }

                    @Override
                    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                        component = value instanceof JComponent ? (JComponent) value :  component;
                         return  component;
                    }

                    @Override
                    public Object getCellEditorValue() {
                              return component;
                    }

                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }

 
    
}
}
