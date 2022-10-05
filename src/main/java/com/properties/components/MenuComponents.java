package com.properties.components;

import com.properties.PropertiesBase;
import com.properties.PropertiesItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;


import static com.properties.PropertiesItem.*;
public class MenuComponents extends ComponentProperties {


    public MenuComponents(PropertiesBase properties) {
        super(properties);
    }

    @Override
    public void createProperties() {
        MenuButton menu = new MenuButton("Components");
        menu.setAlignment(Pos.CENTER);
        menu.setPopupSide(Side.RIGHT);
        menu.getStyleClass().add("my-menuButton");
        menu.getItems().add(newMenuItem("Image", e-> {
            System.out.println("Image");
            ImageProperties img = new ImageProperties(properties);
            properties.getChildren().add(properties.getSize() - 1, img);
        }));
        menu.getItems().add(newMenuItem("Scrips", e-> {
            System.out.println("Scrips");
        }));
        alignment(menu, NONE, NONE, 0, 0);
        getChildren().add(menu);
    }

    private MenuItem newMenuItem(String name, EventHandler<ActionEvent> event)
    {
        MenuItem item = new MenuItem(name);
        item.setOnAction(event);
        return item;
    }


}
