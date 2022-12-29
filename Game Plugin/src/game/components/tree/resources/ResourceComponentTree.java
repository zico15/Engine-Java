package game.components.tree.resources;

import com.tree.TreeViewController;
import game.components.tree.base.BaseResourceComponentTree;
import game.components.tree.base.fileType;
import game.core.system.Icons;
import game.project.prefabs.Prefab;

import java.io.File;

public class ResourceComponentTree extends BaseResourceComponentTree {


    public ResourceComponentTree(Prefab prefab, TreeViewController controller, File file) {
        super(prefab, file, controller, null);
        load(file);
        if (!file.isDirectory())
            setContextMenu(null);
    }

    public void load(File file){

        if (file != null) {
            String name = file.getName().contains(".") ? file.getName().substring(0, file.getName().lastIndexOf(".")) : file.getName();
            setFile(file);
            setValue(name);
            fileType type = BaseResourceComponentTree.getExtensionType(file);
            setIcon(Icons.get(type));
        }
    }

}
