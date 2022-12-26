package game.lib;

import com.system.FileSistem;
import com.view.ComponentView;
import game.components.tree.base.BaseResourceComponentTree;
import game.components.view.objects.GameObjectProperties;
import game.components.view.objects.ScenePanel;
import game.components.view.objects.SceneTreeView;
import game.components.view.resources.ResourceTreeView;
import game.core.project.GameProject;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import plugins.Plugins;

import java.io.File;

public class InstanceLib extends Plugins {

    private ScenePanel scene;

    private  ResourceTreeView resourceTreeView;

    private SceneTreeView sceneTreeView;

    private GameObjectProperties gameObjectProperties;

    @Override
    public void install() {
        TabPane pane = ComponentView.getComponent("tabPaneMain");
        scene = new ScenePanel();
        pane.getTabs().add(scene.getTab());
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
        initMenuTop(ComponentView.getComponent("menuBarTop"));
    }

    private void initMenuTop(MenuBar menu)
    {
        menu.getMenus().forEach(e -> {
            if ("Window".equalsIgnoreCase(e.getText()))
                e.getItems().add(scene.getMenuWindow());
            else if ("File".equalsIgnoreCase(e.getText())) {
                for (MenuItem m : e.getItems()){
                    if ("New Project".equalsIgnoreCase(m.getText())) {
                        System.out.println("File: "+ m.getText());
                        GameProject.creatingMenu(((Menu) m), resourceTreeView);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void uninstall() {
        ComponentView.removeComponent(scene);
        System.out.println("uninstall");
    }
}
