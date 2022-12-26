package com.tree;


import com.properties.components.BaseComponentTree;
import com.system.FileSistem;
import com.system.ImageBase;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Optional;

public class TreeSceneController extends TreeView<String> {

    private Tab tabView;

    public TreeSceneController() {
        setFocused(false);
        getSelectionModel().selectedItemProperty() .addListener((observable, old_val, new_val) -> {
            BaseComponentTree selectedItem = (BaseComponentTree) new_val;
            setContextMenu(selectedItem.getContextMenu());
            selectedItem.preview();
        });
    }

    public Tab getTabView() {
        return tabView;
    }

    public void setTabView(Tab tabView) {
        this.tabView = tabView;
    }

}