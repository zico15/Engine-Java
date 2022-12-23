package com.tree;


import com.system.ImageBase;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;

public class TreeSceneController extends TreeView<String> {
    private static final Image imgScene = ImageBase.getIcons(ImageBase.ICON_SCENE);
    /*
    private TreeItemObject rootItem;
    private Tab tabView;

    public TreeSceneController() {
        setFocused(false);
        getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
                ((TreeItemObject) newValue).preview();
        });
    }

    public void load(TreeItemObject item, GameObject ob) {
        if (ob == null)
            return;
        TreeItemObject i = new TreeItemObject(ob);
        ob.getChildren().forEach(e -> load(i, e));
        if (item == null) {
            setContextMenu(creadMenu());
            rootItem = i;
            setRoot(rootItem);
        } else {
            item.setExpanded(true);
            item.getChildren().add(i);
        }
    }

    public void addItem(TreeItemObject item, GameObject ob) {
        if (ob == null)
            return;
        TreeItemObject i = new TreeItemObject(ob);
        item.setExpanded(true);
        item.ob.addGameObject(ob);
        item.getChildren().add(i);
    }

    public ContextMenu creadMenu() {
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
            if (item != getRoot() && item.delete()) {
                TreeItemObject parent = (TreeItemObject) item.getParent();
                parent.ob.removeObject(item.ob);
                parent.getChildren().remove(item);
            }
            System.out.println("delete");
        });
        return new ContextMenu(addobject, addTileMap, delete);
    }

    public Tab getTabView() {
        return tabView;
    }

    public void setTabView(Tab tabView) {
        this.tabView = tabView;
    }*/
}