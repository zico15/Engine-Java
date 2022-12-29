package game.components.tree.resources;

import com.tree.TreeViewController;
import game.core.objects.Scene;
import game.core.system.GameEngine;
import game.project.prefabs.Prefab;

import java.io.File;

public class SceneResourceComponentTree extends ResourceComponentTree {


    public SceneResourceComponentTree(Prefab prefab, TreeViewController controller, File file) {
        super(prefab, controller, file);
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
