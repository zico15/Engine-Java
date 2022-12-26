package com.properties.components;

import com.tree.TreeViewController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class BaseComponentTree extends  TreeItem<String> {


    private final TreeViewController controller;

    private ContextMenu contextMenu;
    private final ArrayList<BaseComponentTree> baseComponentTrees = new ArrayList<>();
    private final ImageView icon;

    public BaseComponentTree(TreeViewController controller, Image icon){
        this.icon = new ImageView(icon);
        this.icon.setFitWidth(15);
        this.icon.setFitHeight(15);
        this.icon.setFocusTraversable(false);
        this.setValue(getClass().getSimpleName());
        this.setGraphic(this.icon);
        this.controller = controller;
    }

    public final void setIcon(Image img)
    {
        this.icon.setImage(img);
    }
    public final void  addTree(BaseComponentTree tree){
        baseComponentTrees.add(tree);
        setExpanded(true);
        getChildren().add(tree);
    }
    public abstract void preview();

    public TreeViewController getController() {
        return controller;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public void setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }
}
