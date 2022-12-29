package game.core.system;

import com.system.FileSystem;
import com.view.ComponentView;
import game.components.view.objects.GameObjectProperties;
import game.components.view.objects.ScenePanel;
import game.components.view.objects.SceneTreeView;
import game.components.view.resources.ResourceTreeView;
import game.project.GameProject;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.File;

public class GameEngine {

    public static ScenePanel scene;

    public static  ResourceTreeView resourceTreeView;

    public static  SceneTreeView sceneTreeView;

    public static  GameObjectProperties gameObjectProperties;

    public static final GameProject gameProject = new GameProject();


    public static void creatingMenu()
    {

        MenuBar menuTop = ComponentView.getComponent("menuBarTop");
        menuTop.getMenus().forEach(menu -> {
            if ("Window".equalsIgnoreCase(menu.getText()))
                menu.getItems().add(scene.getMenuWindow());
            else if ("File".equalsIgnoreCase(menu.getText())) {
                for (MenuItem m : menu.getItems()){
                    if ("New Project".equalsIgnoreCase(m.getText())) {
                        System.out.println("File: "+ m.getText());
                        MenuItem newProject = new MenuItem("Game Java");
                        newProject.setOnAction(e -> {
                            File file = FileSystem.saveFile();
                            if (file != null) {
                                file.mkdirs();
                                gameProject.load(file);
                            }
                        });
                        MenuItem openProject = new MenuItem("Open");
                        openProject.setOnAction(e -> {
                            File file = FileSystem.openFolder();
                            if (file != null && file.exists() && file.isDirectory())
                                gameProject.load(file);
                        });
                        ((Menu) m).getItems().add(newProject);
                        ((Menu) m).getItems().add(openProject);
                    } else if ("Save".equalsIgnoreCase(m.getText())) {
                        System.out.println("MenuSave");
                        m.setOnAction(e-> gameProject.save());
                    }
                }
            }
        });

    }
}
