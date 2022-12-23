package com.lib;

import com.view.ComponentView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import plugins.Plugins;

public class InstanceLib extends Plugins {

    private ScenePanel scene;
    @Override
    public void install() {
        TabPane pane = ComponentView.getComponent("tabPaneMain");
        scene = new ScenePanel();
        pane.getTabs().add(scene.getTab());
        ComponentView.addComponent(scene);
        MenuBar menu = ComponentView.getComponent("menuBarTop");
        menu.getMenus().forEach(e -> {
            if ("Window".equalsIgnoreCase(e.getText()))
                e.getItems().add(scene.getMenuWindow());
        });
        System.out.println("install02");
    }

    @Override
    public void uninstall() {
        ComponentView.removeComponent(scene);
        System.out.println("uninstall");
    }
}
