package com.tree;

import com.system.ImageBase;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Optional;

public class TreeResourceController extends TreeView<String> {

    private final Image imgFolder = ImageBase.getIcons(getClass().getResource("/com/icons/folder.png").toExternalForm());

    private TreeItem rootItem = null;

    public TreeResourceController() {
        //rootItem = new TreeItem("Scene", new ImageView(imgFolder));
        setContextMenu(creadMenu());
        //setRoot(getRootItem());
        setFocused(false);
    }

    public void setName(String name) {
        if (getRootItem() == null)
        {
            rootItem = new TreeItem(name, new ImageView(imgFolder));
            setRoot(getRootItem());
        }
        else
            getRootItem().setValue(name);
    }

    public TreeItem addItem(TreeItem item, String name, String type) {
        TreeItem i = new TreeItem(name, new ImageView(imgFolder));
        item.getChildren().add(i);
        return i;
    }

    public void clear()
    {
        if (getRootItem() != null)
            getRootItem().getChildren().clear();
    }

    public void load(File file, TreeItem item)
    {
        if (!file.exists() || getRootItem() == null)
                return;
        File files[] = file.listFiles();
        for (File f : files)
        {
            TreeItem i = addItem(item, f.getName(), "Folder");
            if (f.isDirectory())
                load(f, i);
        }
    }

    private ContextMenu creadMenu() {
        MenuItem addobject = new MenuItem("new folder");
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
                addItem(item, entered, "Folder");
            System.out.println(entered);
        });
        MenuItem addscript= new MenuItem("new script");
        MenuItem delete = new MenuItem("delete");
        delete.setOnAction(e -> {
            TreeItem item = getSelectionModel().getSelectedItem();
            if (item != getRoot())
                item.getParent().getChildren().remove(item);
            System.out.println("delete");
        });
        return new ContextMenu(addobject, addscript, delete);
    }

    public TreeItem getRootItem() {
        return rootItem;
    }
}