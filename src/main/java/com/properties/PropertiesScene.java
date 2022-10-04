package com.properties;

import com.tree.TreeItemObject;

import static com.properties.PropertiesItem.*;

public class PropertiesScene extends PropertiesBase {

    public PropertiesScene(TreeItemObject item){
        super(item);
    }

    @Override
    public void createProperties() {
        getChildren().add(itemTitle(getItem(),20));
        getChildren().add(itemName(getItem(),25));
    }
}
