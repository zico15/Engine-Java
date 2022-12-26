package com.properties.components;

import com.tree.TreeSceneController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class BaseComponentTree extends  TreeItem<String> {


    private final TreeSceneController controller;

    private ContextMenu contextMenu;
    private final ArrayList<BaseComponentTree> baseComponentTrees = new ArrayList<>();

    public BaseComponentTree(TreeSceneController controller, Image icon){
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        imageView.setFocusTraversable(false);
        this.setValue(getClass().getSimpleName());
        this.setGraphic(imageView);
        this.controller = controller;
    }

    public final void  addTree(BaseComponentTree tree){
        baseComponentTrees.add(tree);
        setExpanded(true);
        getChildren().add(tree);
    }
    public abstract void preview();

    public TreeSceneController getController() {
        return controller;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public void setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }
}
