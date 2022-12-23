package com.properties;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;



public abstract class PropertiesBase extends VBox {



    public abstract void createProperties();

    public void onMouseMove(MouseEvent e) {

    }

    public void onMouseClick(MouseEvent e) {

    }



    public final int getSize() {
        return getChildren().size();
    }
}
