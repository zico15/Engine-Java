package game.lib;

import com.view.ComponentView;
import game.components.view.GameObjectProperties;
import game.components.view.ScenePanel;
import game.components.view.SceneTreeView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
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
        TabPane properties = ComponentView.getComponent("tabProperties");
        SceneTreeView sceneTreeView = new SceneTreeView(scene);
        ComponentView.addComponent(sceneTreeView);
        properties.getTabs().add(sceneTreeView.getTabView());
        properties.getTabs().add(new GameObjectProperties(properties).getTab());

        ToggleButton toggleButton = ComponentView.getComponent("enginePlayer");
        toggleButton.setOnAction(e-> {
            System.out.println("toggleButton");
            scene.drawing();
        });
        System.out.println("install02");
    }

    @Override
    public void uninstall() {
        ComponentView.removeComponent(scene);
        System.out.println("uninstall");
    }
}
