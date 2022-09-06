package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController extends Control {

    @FXML
    private TreeView<String> treeView;
    TreeItem rootItem = new TreeItem("Tutorials");
    private final Node rootIcon = new ImageView( );

    @FXML
    private void initialize()
    {

        System.out.println("initialize");
        TreeItem webItem = new TreeItem("Web Tutorials", rootIcon);
        webItem.getChildren().add(new TreeItem("HTML  Tutorial", rootIcon));
        webItem.getChildren().add(new TreeItem("HTML5 Tutorial", rootIcon));
        webItem.getChildren().add(new TreeItem("CSS Tutorial", rootIcon));
        webItem.getChildren().add(new TreeItem("SVG Tutorial", rootIcon));
        rootItem.getChildren().add(webItem);
        treeView.setRoot(rootItem);
    }
    @FXML
    protected void onHelloButtonClick(){

        TreeItem t = new TreeItem<>();
        t.setValue("sasd");
        treeView.getSelectionModel().getSelectedItems().add(t);
    }
}