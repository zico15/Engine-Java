package com.tree;

import com.properties.components.BaseComponentTree;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.io.File;
import java.util.List;

public class TreeViewController extends TreeView<String> {

    private Tab tabView;

    public TreeViewController() {
        setFocused(false);
        getSelectionModel().selectedItemProperty().addListener((observable, old_val, new_val) -> {
            if (new_val != null) {
                BaseComponentTree item = (BaseComponentTree) new_val;
                setContextMenu(item.getContextMenu());
                selectedItem(item);
                item.preview();
            }
        });
    }

    /***
     * the function is called by the TreeView selection
     * event passing the selected item in the tree as parameter
     * ***/
    public void selectedItem(BaseComponentTree item){
    }

    public Tab getTabView() {
        return tabView;
    }

    public void setTabView(Tab tabView) {
        this.tabView = tabView;
    }

}