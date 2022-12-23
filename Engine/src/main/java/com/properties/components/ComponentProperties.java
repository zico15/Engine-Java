package com.properties.components;

import com.properties.PropertiesBase;
import javafx.scene.layout.AnchorPane;

public abstract class ComponentProperties extends AnchorPane {

    public PropertiesBase properties;


    public ComponentProperties(PropertiesBase properties) {

        setMinHeight(20);
        setPrefWidth(0);
        setMinWidth(0);
        createProperties();
    }

    public abstract void createProperties();


}
