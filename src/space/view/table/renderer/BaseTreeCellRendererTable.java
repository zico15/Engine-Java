/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.view.table.renderer;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author 35191
 */
public class BaseTreeCellRendererTable implements TreeCellRenderer {
        private  JComponent component;

        public BaseTreeCellRendererTable() {
//            label = new JLabel(); 
//            label.setBackground(new Color(198, 226, 255));
        }

    
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                      boolean leaf, int row, boolean hasFocus) {
            leaf = true;
            Object o = ((DefaultMutableTreeNode) value).getUserObject();
           component = o instanceof JComponent ? (JComponent) o :  component;
         
            return component;
        }

 

   
    }

 
