package com.properties;

import com.properties.components.MenuComponents;
import com.tree.TreeItemObject;

public class PropertiesScene extends PropertiesBase{

    public PropertiesScene(TreeItemObject item){
        super(item);
        System.out.println("PropertiesScene");
    }

    @Override
    public void createProperties() {
       /* properties.setFocusTraversable(false);
        addItemProperties(itemTitle(item,20));
        addItemProperties(itemName(item,25));
        addItemProperties(itemPosition(item,20));*/
        System.out.println("PropertiesScene");
    }
}
