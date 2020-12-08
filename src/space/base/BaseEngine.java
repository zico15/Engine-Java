/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.base;

import javax.swing.JMenuBar;
import javax.swing.JTree;
import space.view.table.jTabbedPaneView;

/**
 *
 * @author 35191
 */
public class BaseEngine {
    
    private static JMenuBar menu;
    private static JTree project;
    private static JTree resource;
    private static jTabbedPaneView paneMain;
    
    public static JMenuBar getMenu(){ return menu; }
        
    public static String getIconDefault(String fileName){
     return null;
    }
    
    public static JTree getProjects(){ return project;}
    
    public static JTree getResource(){ return resource;}

    /**
     * @param aMenu the menu to set
     */
    public static void setMenu(JMenuBar aMenu) {
        menu = aMenu;
    }

    /**
     * @param aProject the project to set
     */
    public static void setProject(JTree aProject) {
        project = aProject;
    }

    /**
     * @param aResource the resource to set
     */
    public static void setResource(JTree aResource) {
        resource = aResource;
    }

    /**
     * @return the paneMain
     */
    public static jTabbedPaneView getPaneMain() {
        return paneMain;
    }

    /**
     * @param aPaneMain the paneMain to set
     */
    public static void setPaneMain(jTabbedPaneView aPaneMain) {
        paneMain = aPaneMain;
    }
    
    public static void reloadView(){
    
        if(menu!=null) menu.validate();
        if(paneMain!=null) paneMain.validate();
        if(resource!=null) resource.validate();
        if(project!=null) project.validate();
    }
}
