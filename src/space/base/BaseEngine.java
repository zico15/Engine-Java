/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.base;

import javax.swing.JMenu;
import javax.swing.JTree;

/**
 *
 * @author 35191
 */
public class BaseEngine {
    
    public JMenu[] getMenusBar(){ return null; }
    
    
    public String getIconDefault(String fileName){
     return null;
    }
    
    public JTree getScene(){ return new JTree();}
    
    public JTree getResource(){ return new JTree();}
}
