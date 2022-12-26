package game.components.tree.resources;

import com.tree.TreeViewController;
import game.components.tree.base.BaseResourceComponentTree;
import game.components.tree.base.fileType;
import game.core.project.GameProject;
import game.lib.Icons;
import javafx.scene.control.ContextMenu;

import java.io.File;

public class ProjectComponentTree extends BaseResourceComponentTree {

    private final GameProject gameProject = new GameProject();
    public ProjectComponentTree(TreeViewController controller) {
        super(null, controller, Icons.get(fileType.FOLDER_ROOT));
        setValue("");
    }

    public void load(File file){
        setFile(file);
        setValue(file.getName());
        gameProject.setDirectory(file);
    }

}
