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
    
    private static JMenu menu;
    private static JTree project;
    private static JTree resource;
    
    
    public static JMenu getMenu(){ return menu; }
        
    public String getIconDefault(String fileName){
     return null;
    }
    
    public JTree getProjects(){ return project;}
    
    public JTree getResource(){ return resource;}
    
    
}
