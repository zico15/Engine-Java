package com.properties.components;

import com.properties.PropertiesBase;
import com.properties.PropertiesItem;
import com.tree.TreeItemObject;
import engine2d.components.ComponentBase;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import static com.properties.PropertiesItem.*;
import static com.properties.PropertiesItem.NONE;

public abstract class ComponentProperties extends AnchorPane {

    public PropertiesBase properties;
    public ComponentBase component;
    public TreeItemObject item;

    public ComponentProperties(PropertiesBase properties){
        this.properties = properties;
        this.item = properties.getItem();
        setMinHeight(20);
        alignment(this, NONE, NONE, 0, 0);
        setPrefWidth(0);
        setMinWidth(0);
        createProperties();
    }

    public abstract void createProperties();



}
