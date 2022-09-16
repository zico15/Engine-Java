package com.tree;

import com.system.ImageBase;
import engine2d.objects.GameObject;
import engine2d.objects.Scene;
import engine2d.objects.TileMaps;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import java.util.Optional;

public class TreeSceneController extends TreeView<String> {
    private TreeItemObject rootItem;

    public TreeSceneController() {
        Image imgScene = ImageBase.getIcons(ImageBase.ICON_SCENE);
        rootItem = new TreeItemObject(new Scene("Scene"));
        setContextMenu(creadMenu());
        setRoot(rootItem);
        setFocused(false);
        getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> ((TreeItemObject) newValue).preview());
    }

    public void addItem(TreeItem item, GameObject ob) {
        if (ob == null)
            return;
        TreeItemObject i = new TreeItemObject(ob);
        item.setExpanded(true);
        item.getChildren().add(i);
    }

    private ContextMenu creadMenu() {
        MenuItem addobject = new MenuItem("create object");
        addobject.setOnAction(e -> {
            TreeItemObject item = (TreeItemObject) getSelectionModel().getSelectedItem();
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Object");
            dialog.setHeaderText("Name");
            Optional<String> result = dialog.showAndWait();
            String entered = null;
            if (result.isPresent())
                entered = result.get();

            if (entered != null)
                addItem(item, new GameObject(entered));
            System.out.println(entered);
        });
        MenuItem addTileMap = new MenuItem("create tileMap");
        addTileMap.setOnAction(e -> {
            TreeItemObject item = (TreeItemObject) getSelectionModel().getSelectedItem();
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("TileMaps");
            dialog.setHeaderText("Name");
            Optional<String> result = dialog.showAndWait();
            String entered = null;
            if (result.isPresent())
                entered = result.get();

            if (entered != null)
                addItem(item, new TileMaps(entered));
            System.out.println(entered);
        });
        MenuItem delete = new MenuItem("delete");
        delete.setOnAction(e -> {
            TreeItemObject item = (TreeItemObject) getSelectionModel().getSelectedItem();
            if (item != getRoot() && item.delete())
                item.getParent().getChildren().remove(item);
            System.out.println("delete");
        });
        return new ContextMenu(addobject, addTileMap,  delete);
    }
}