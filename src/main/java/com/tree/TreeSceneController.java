package com.tree;

import com.system.ImageBase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Optional;

public class TreeSceneController extends TreeView<String> {

    private final Image imgFolder = ImageBase.getIcons(getClass().getResource("/com/icons/object.png").toExternalForm());

    private TreeItem rootItem;

    public TreeSceneController() {
        Image imgScene = ImageBase.getIcons(getClass().getResource("/com/icons/scene.png").toExternalForm());
        rootItem = new TreeItem("Scene", new ImageView(imgScene));
        setContextMenu(creadMenu());
        setRoot(rootItem);
        setFocused(false);
    }

    public void addItem(TreeItem item, String name) {
        TreeItem i = new TreeItem(name, new ImageView(imgFolder));
        item.getChildren().add(i);
    }

    private ContextMenu creadMenu() {
        MenuItem addobject = new MenuItem("create object");
        addobject.setOnAction(e -> {
            TreeItem item = getSelectionModel().getSelectedItem();
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
        MenuItem addscript= new MenuItem("create script");
        MenuItem delete = new MenuItem("delete");
        delete.setOnAction(e -> {
            TreeItem item = getSelectionModel().getSelectedItem();
            if (item != getRoot())
                item.getParent().getChildren().remove(item);
            System.out.println("delete");
        });
        return new ContextMenu(addobject, addscript, delete);
    }
}