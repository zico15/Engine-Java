package com.tree;

import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class TreeResourceController extends TreeView<String> {

    private TreeItemResource rootItem = null;

    public TreeResourceController() {
        setFocused(false);
    }

    public void setRootFile(File file) {
        if (getRootItem() == null)
        {
            rootItem = new TreeItemResource(file);
            setRoot(getRootItem());
            setContextMenu(creadMenu());
        }
        else
            getRootItem().setFile(file);
    }

    public TreeItem addItem(TreeItem item, File file) {
        TreeItemResource i = new TreeItemResource(file);
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
            TreeItem i = addItem(item, f);
            if (f.isDirectory())
                load(f, i);
        }
    }

    private ContextMenu creadMenu() {
        MenuItem addobject = new MenuItem("new folder");
        addobject.setOnAction(e -> {
            TreeItemResource item = (TreeItemResource) getSelectionModel().getSelectedItem();
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Folder");
            dialog.setHeaderText("Name");
            Optional<String> result = dialog.showAndWait();
            String entered = null;
            if (result.isPresent())
                entered = result.get();
            if (entered != null)    {
                File f = new File(item.file, entered);
                f.mkdir();
                addItem(item, f);
            }
            System.out.println(entered);
        });
        MenuItem addscript= new MenuItem("new script");
        addscript.setOnAction(e -> {
            TreeItemResource item = (TreeItemResource) getSelectionModel().getSelectedItem();
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Script");
            dialog.setHeaderText("Name");
            Optional<String> result = dialog.showAndWait();
            String entered = null;
            if (result.isPresent())
                entered = result.get();

            if (entered != null){
                try {
                    File f = new File(item.file, entered + ".java");
                    f.createNewFile();
                    addItem(item, f);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
             }
            System.out.println(entered);
        });
        MenuItem delete = new MenuItem("delete");
        delete.setOnAction(e -> {
            TreeItemResource item = (TreeItemResource) getSelectionModel().getSelectedItem();
            if (item != getRoot() && item.delete())
                item.getParent().getChildren().remove(item);

            System.out.println("delete");
        });
        return new ContextMenu(addobject, addscript, delete);
    }

    public TreeItemResource getRootItem() {
        return rootItem;
    }
}