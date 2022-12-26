package game.core.project;

import com.project.Project;
import com.system.FileSistem;
import game.components.view.resources.ResourceTreeView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.File;

public class GameProject extends Project {

    public static void creatingMenu(Menu menu, ResourceTreeView resourceTreeView)
    {
        System.out.println("creatingMenu");
        MenuItem newProject = new MenuItem("Game Java");
        newProject.setOnAction(e -> {
            File file = FileSistem.saveFile();
            if (file != null) {
                file.mkdirs();
                resourceTreeView.load(file);
            }
        });

        MenuItem openProject = new MenuItem("Open");
        menu.getItems().add(newProject);
        menu.getItems().add(openProject);
    }
}
