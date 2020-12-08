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
    
    public static JMenu menuMain;
    public static JTree project;
    public static JTree resource;
    //public static JMenu project;
    
    public BaseEngine(){
    
    }
    
    public static JMenu getMenu(){ return menuMain; }
        
    public String getIconDefault(String fileName){
     return null;
    }
    
    public JTree getProjects(){ return new JTree();}
    
    public JTree getResource(){ return new JTree();}
    
    
}
