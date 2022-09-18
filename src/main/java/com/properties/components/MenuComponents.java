package com.properties.components;

import com.properties.PropertiesBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.TextAlignment;

import java.util.function.Consumer;

public class MenuComponents extends ComponentProperties {

    private final MenuButton menu = new MenuButton("Components");
    public MenuComponents(PropertiesBase properties) {
        super(properties);
        if (menu.getItems().size() != 0)
            return;
        menu.setAlignment(Pos.CENTER);
        menu.setPopupSide(Side.RIGHT);
        menu.getStyleClass().add("my-menuButton");
        menu.getItems().add(newMenuItem("Image", e-> {
            System.out.println("Image");
            ImageProperties img = new ImageProperties(properties);
            properties.addItemProperties(properties.getSize() - 1, img.getProperties());
        }));
        menu.getItems().add(newMenuItem("Scrips", e-> {
            System.out.println("Scrips");
        }));
    }
    private final MenuItem newMenuItem(String name, EventHandler<ActionEvent> event)
    {
        MenuItem item = new MenuItem(name);
        item.setOnAction(event);
        return item;
    }

    @Override
    public Node getProperties() {
        return PropertiesBase.newItem(menu, 20);
    }
}
