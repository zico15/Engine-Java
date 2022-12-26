package game.components.view;

import com.tree.TreeBase;
import com.tree.TreeSceneController;
import game.components.tree.SceneComponentTree;


public class SceneTreeView extends TreeSceneController {

    private SceneComponentTree scene;
    public SceneTreeView(ScenePanel scenePanel){
        setTabView(TreeBase.newTab("Scene", this));
        setId("sceneTreeView");
        scene = new SceneComponentTree(this, scenePanel);
        setRoot(scene);
    }


    public void addGameObject()
    {

    }
}
