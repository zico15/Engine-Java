package com.view;

import com.tree.TreeBase;
import com.tree.TreeResourceController;
import com.tree.TreeSceneController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;


public abstract class ComponentView extends Control {

    private static HashMap<String, Parent> nodes = new HashMap<>();
    @FXML
    private ProgressBar progressBar;
    @FXML
    private MenuBar menuBarTop;
    @FXML
    private TabPane tabProperties;
    @FXML
    private TabPane tabPaneMain;

    @FXML
    private void initialize() {
        addComponent(progressBar);
        addComponent(menuBarTop);
        addComponent(tabProperties);
        addComponent(tabPaneMain);
        initializeView();
    }

    abstract void initializeView();

    public static boolean addComponent(Parent node){
        try{
            if (nodes == null || nodes.containsKey(node.getId()))
                return false;
            nodes.put(node.getId(), node);
        } catch (NullPointerException e)
        {
            System.err.println("addComponent: NullPointerException -> " + node);
            return false;
        }
        return true;
    }
    public static boolean setComponent(Parent node){
        try{
                if (node == null)
                     return false;
                nodes.put(node.getId(), node);
        } catch (NullPointerException e)
        {
            System.err.println("setComponent: NullPointerException");
            return false;
        }
        return true;
    }
    public static boolean removeComponent(Parent node){
        try{
        if (node == null)
            return false;
        nodes.remove(node.getId());
        } catch (NullPointerException e)
        {
            System.err.println("removeComponent: NullPointerException");
            return false;
        }
        return true;
    }
    public static <T> T getComponent(String id) {
         return (T) nodes.get(id);
     }
}
