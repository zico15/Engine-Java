package com.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.HashMap;


public abstract class ComponentView extends Control {

    private static HashMap<String, Node> nodes = new HashMap<>();
    @FXML
    private ProgressBar progressBar;
    @FXML
    private MenuBar menuBarTop;
    @FXML
    private TabPane tabProperties;
    @FXML
    private TabPane tabPaneMain;

    @FXML
    private ToggleButton enginePlayer;

    @FXML
    private void initialize() {
        addComponent(progressBar);
        addComponent(menuBarTop);
        addComponent(tabProperties);
        addComponent(tabPaneMain);
        addComponent(enginePlayer);
        initializeView();
    }

    abstract void initializeView();

    public static boolean addComponent(Node node){
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
    public static boolean setComponent(Node node){
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
    public static boolean removeComponent(Node node){
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
