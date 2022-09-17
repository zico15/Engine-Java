package com.properties.components;

import com.properties.PropertiesBase;
import com.tree.TreeItemObject;
import engine2d.components.ComponentBase;
import javafx.scene.Node;

public class CompomentProperties {

    public PropertiesBase properties;
    public ComponentBase component;
    public TreeItemObject item;

    public CompomentProperties(PropertiesBase properties){
        this.properties = properties;
        this.item = properties.item;
    }

    public Node getProperties() {
        return null;
    }


}
