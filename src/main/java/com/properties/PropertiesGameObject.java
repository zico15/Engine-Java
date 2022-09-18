package com.properties;

import com.properties.components.ImageProperties;
import com.properties.components.MenuComponents;
import com.tree.TreeItemObject;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PropertiesGameObject extends PropertiesBase{

    public PropertiesGameObject(TreeItemObject item){
        super(item);
    }

    @Override
    public void createProperties() {
        properties.setFocusTraversable(false);
        addItemProperties(itemTitle(item,20));
        addItemProperties(itemName(item,25));
        addItemProperties(itemPosition(item,20));
        addItemProperties(new MenuComponents(this).getProperties());
    }

}
