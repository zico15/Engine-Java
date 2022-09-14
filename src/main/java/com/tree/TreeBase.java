package com.tree;

import javafx.scene.control.TreeView;

public class TreeBase extends TreeView {

    @Override
    public String getTypeSelector() {
        System.out.println("getTypeSelector");
        return super.getTypeSelector();
    }
}
