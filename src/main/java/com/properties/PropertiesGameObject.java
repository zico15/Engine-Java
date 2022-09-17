package com.properties;

import com.tree.TreeItemObject;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PropertiesGameObject extends PropertiesBase{


    public PropertiesGameObject(TreeItemObject item){
        super(item);
    }

    private VBox gameObject(){
        VBox v = new VBox();
        v.setFocusTraversable(false);
        alignment(v ,NONE, NONE, 0,0);
        v.getChildren().add(itemTitle(item,20));
        v.getChildren().add(itemName(item,20));
        v.getChildren().add(itemPosition(item,20));
        return v;
    }

    @Override
    public Node getProperties() {
        if (properties != null)
            return properties;
        properties = new AnchorPane();
        alignmentAll(properties);
        properties.getChildren().add(gameObject());
        return properties;
    }
}
