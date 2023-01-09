package game.lib;

import com.view.ComponentView;
import game.components.view.objects.GameObjectProperties;
import game.components.view.objects.ScenePanel;
import game.components.view.objects.SceneTreeView;
import game.components.view.resources.ResourceTreeView;
import game.project.GameEngine;
import javafx.scene.control.*;
import plugins.Plugins;

import static game.project.GameEngine.*;

public class InstanceLib extends Plugins {

    @Override
    public void install() {
        GameEngine.tabPaneMain = ComponentView.getComponent("tabPaneMain");
        scene = new ScenePanel();
        GameEngine.tabPaneMain.getTabs().add(scene.getTab());
        ComponentView.addComponent(scene);
        TabPane properties = ComponentView.getComponent("tabProperties");
        sceneTreeView = new SceneTreeView(scene);
        ComponentView.addComponent(sceneTreeView);
        properties.getTabs().add(sceneTreeView.getTabView());
        resourceTreeView = new ResourceTreeView();
        properties.getTabs().add(resourceTreeView.getTabView());
        gameObjectProperties = new GameObjectProperties(properties);
        properties.getTabs().add(gameObjectProperties.getTabView());
        ToggleButton toggleButton = ComponentView.getComponent("enginePlayer");
        toggleButton.setOnAction(e -> {
            System.out.println("toggleButton");
            scene.drawing();
        });
        creatingMenu();
    }



    @Override
    public void uninstall() {
        ComponentView.removeComponent(scene);
        System.out.println("uninstall");
    }
}
