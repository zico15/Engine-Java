package com.lib;

import com.tree.TreeBase;
import com.view.ComponentView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class ScenePanel extends Pane {

    private Tab tab;
    public ScenePanel(){
        setId("gameScenePanel");
        tab = TreeBase.newTab("Scene", this);
    }

    public Tab getTab(){
        return tab;
    }
    public MenuItem getMenuWindow()
    {
        MenuItem m = new MenuItem("Game Scene");
        m.setOnAction(e -> {
            TabPane pane = ComponentView.getComponent("tabPaneMain");
            if(!pane.getTabs().contains(getTab()))
                pane.getTabs().add(getTab());
            System.out.println("menuOn: scene");
        });
        System.out.println("menu: add");
        return m;
    }
}
