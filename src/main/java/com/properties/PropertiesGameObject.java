package com.properties;

import com.properties.components.ImagePropertides;
import com.tree.TreeItemObject;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PropertiesGameObject extends PropertiesBase{

    private VBox v = new VBox();
    public PropertiesGameObject(TreeItemObject item){
        super(item);
    }

    private VBox gameObject(){
        v.setFocusTraversable(false);
        alignment(v ,NONE, NONE, 0,0);
        v.getChildren().add(itemTitle(item,20));
        v.getChildren().add(itemName(item,20));
        v.getChildren().add(itemPosition(item,20));
        Button add = new Button("Component");
        add.setOnAction(e -> {
            System.out.println("Component");
            ImagePropertides img = new ImagePropertides(this);
            v.getChildren().add(img.getProperties());
        });
        v.getChildren().add(newItem(add, 20));
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
