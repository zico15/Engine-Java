package game.components.tree.resources;

import com.tree.TreeViewController;
import game.components.tree.base.BaseResourceComponentTree;
import game.components.tree.base.fileType;
import game.core.project.GameProject;
import game.lib.Icons;
import javafx.scene.image.ImageView;

import java.io.File;

public class FolderComponentTree extends BaseResourceComponentTree {


    public FolderComponentTree(TreeViewController controller, File file) {
        super(file, controller, null);
        load(file);
    }

    public void load(File file){
        setFile(file);
        setValue(file.getName());
        fileType type = BaseResourceComponentTree.getExtensionType(file);
        setIcon(Icons.get(type));
    }

}
