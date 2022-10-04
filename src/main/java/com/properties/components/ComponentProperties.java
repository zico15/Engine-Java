package com.properties.components;

import com.properties.PropertiesBase;
import com.properties.PropertiesItem;
import com.tree.TreeItemObject;
import engine2d.components.ComponentBase;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public abstract class ComponentProperties extends VBox {

    public PropertiesBase properties;
    public ComponentBase component;
    public TreeItemObject item;

    public ComponentProperties(PropertiesBase properties){
        this.properties = properties;
        this.item = properties.getItem();
        createProperties();
    }

    public abstract void createProperties();



}
