package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class HelloController extends Control {

    @FXML
    private TreeView<String> treeView;


    @FXML
    protected void onHelloButtonClick(){
        System.out.println("sdsad");
        TreeItem t = new TreeItem<>();
        t.setValue("sasd");
        treeView.getSelectionModel().getSelectedItems().add(t);
    }
}