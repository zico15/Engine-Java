package com.properties;

import com.list.TextFieldNumber;
import com.properties.components.MenuComponents;
import com.tree.TreeItemObject;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class PropertiesScene extends PropertiesBase{

    public PropertiesScene(TreeItemObject item){
        super(item);
    }

    @Override
    public void createProperties() {
        properties.setFocusTraversable(false);
        addItemProperties(itemTitle(item,20));
        addItemProperties(itemName(item,25));
        addItemProperties(PropertiesBase.itemSize(item, 20));
    }
}
