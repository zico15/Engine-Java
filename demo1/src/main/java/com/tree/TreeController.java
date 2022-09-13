package com.tree;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class TreeController  extends Control {

    @FXML
    private TreeView<String> treeView;

    private TreeItem rootItem = new TreeItem("Tutorials");
    private final Image imgFolder = new Image(getClass().getResource("/com/treeView/folder.png").toExternalForm());

    @FXML
    private void initialize()
    {

            System.out.println("img");
           // rootIcon = new ImageView(new Image(getClass().getResource("/com/treeView/folder.png").toExternalForm()));

        System.out.println("initialize");
        TreeItem webItem = new TreeItem("Web Tutorials", new ImageView(imgFolder));
        webItem.getChildren().add(new TreeItem("HTML  Tutorial", new ImageView(imgFolder)));
        webItem.getChildren().add(new TreeItem("HTML5 Tutorial", new ImageView(imgFolder)));
        webItem.getChildren().add(new TreeItem("CSS Tutorial", new ImageView(imgFolder)));
        webItem.getChildren().add(new TreeItem("SVG Tutorial", new ImageView(imgFolder)));
        rootItem.getChildren().add(webItem);
        treeView.setRoot(rootItem);
    }
}