package com.tree;

import com.project.Project;
import com.system.FileSistem;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class TreeResourceController extends TreeView<String> {

    private TreeItemResource rootItem = null;
    private Tab tabView;
    public TreeResourceController() {
        setFocused(false);
        getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> ((TreeItemResource) newValue).preview());
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
        item.setExpanded(true);
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
            if (entered != null && item.file.isDirectory())    {
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

            if (entered != null && item.file.isDirectory()){
                    File f = new File(item.file, entered + ".java");
                    FileSistem.createFileJava(f);
                    addItem(item, f);
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

    public Tab getTabView() {
        return tabView;
    }

    public void setTabView(Tab tabView) {
        this.tabView = tabView;
    }
}