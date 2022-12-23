package com.tree;


import com.system.ImageBase;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;

public class TreeSceneController extends TreeView<String> {
    private static final Image imgScene = ImageBase.getIcons(ImageBase.ICON_SCENE);

    private Tab tabView;

    public TreeSceneController() {
        setFocused(false);
        getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
    }


    public Tab getTabView() {
        return tabView;
    }

    public void setTabView(Tab tabView) {
        this.tabView = tabView;
    }
}