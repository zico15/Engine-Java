package game.components.tree.resources;

import com.tree.TreeViewController;
import game.components.tree.base.BaseResourceComponentTree;
import game.components.tree.base.fileType;
import game.core.objects.Scene;
import game.core.system.GameEngine;
import game.core.system.Icons;

import java.io.File;

public class SceneResourceComponentTree extends ResourceComponentTree {


    public SceneResourceComponentTree(TreeViewController controller, File file) {
        super(controller, file);
        System.out.println("SceneResourceComponentTree");
        load(file);
    }

    @Override
    public void preview() {
        String packageName = getSubFile();
        packageName = packageName.replace(".scene", "");
        System.out.println("preview: scene: "+packageName);
        for(Scene s : GameEngine.gameProject.getScenes())
        {
            if (packageName != null && packageName.equals(s.getPackage())) {
                GameEngine.sceneTreeView.load(s);
                break;
            }
        }
    }


}
