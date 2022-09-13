package com;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController extends Control {

    @FXML
    private AnchorPane panelProject;

    @FXML
    private void initialize()
    {

        System.out.println("initialize");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/treeView/tree-view.fxml"));
            Node t = loader.load();
            AnchorPane.setTopAnchor(t, 30.0);
            AnchorPane.setBottomAnchor(t, 0.0);
            AnchorPane.setLeftAnchor(t, 0.0);
            AnchorPane.setRightAnchor(t, 0.0);
            panelProject.getChildren().add(t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onHelloButtonClick(){

        TreeItem t = new TreeItem<>();
        t.setValue("sasd");
    }
}