package com.tree;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;

public class TreeController extends Control {

    private final Image imgFolder = new Image(getClass().getResource("/com/treeView/object.png").toExternalForm());
    @FXML
    private TreeView<String> treeView;
    private TreeItem rootItem;

    @FXML
    private void initialize() {
        Image imgScene = new Image(getClass().getResource("/com/treeView/scene.png").toExternalForm());
        rootItem = new TreeItem("Scene", new ImageView(imgScene));
        treeView.setContextMenu(creadMenu());
        addItem(rootItem, "asdd");
        addItem(rootItem, "dsf");
        addItem(rootItem, "lj");
        treeView.setRoot(rootItem);
    }

    private void addItem(TreeItem item, String name) {
        TreeItem i = new TreeItem(name, new ImageView(imgFolder));
        //i.().add(MenuObject.creadMenu());
        item.getChildren().add(i);
    }

    private ContextMenu creadMenu() {
        MenuItem addobject = new MenuItem("create object");
        addobject.setOnAction(e -> {
            TreeItem item = treeView.getSelectionModel().getSelectedItem();
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Object");
            dialog.setHeaderText("Name");
            Optional<String> result = dialog.showAndWait();
            String entered = null;
            if (result.isPresent())
                entered = result.get();

            if (entered != null)
                addItem(item, entered);
            System.out.println(entered);
        });
        MenuItem delete = new MenuItem("delete");
        delete.setOnAction(e -> {
            TreeItem item = treeView.getSelectionModel().getSelectedItem();
            if (item != treeView.getRoot())
                item.getParent().getChildren().remove(item);
            System.out.println("delete");
        });
        return new ContextMenu(addobject, delete);
    }
}