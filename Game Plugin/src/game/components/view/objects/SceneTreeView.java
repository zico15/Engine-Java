package game.components.view.objects;

import com.properties.components.BaseComponentTree;
import com.tree.TreeBase;
import com.tree.TreeViewController;
import game.components.tree.base.BaseGameComponentTree;
import game.components.tree.objects.GameObjectComponentTree;
import game.components.tree.objects.SceneComponentTree;
import game.core.objects.GameObject;
import game.core.objects.Scene;
import game.project.GameEngine;


public class SceneTreeView extends TreeViewController {

    private SceneComponentTree componentTree;
    private ScenePanel scenePanel;

    public SceneTreeView(ScenePanel scenePanel) {
        setTabView(TreeBase.newTab("Scene", this));
        setId("sceneTreeView");
        this.scenePanel = scenePanel;
    }

    public void load(Scene scene)
    {
        componentTree = new SceneComponentTree(this, scenePanel);
        componentTree.setGameObject(scene);
        setRoot(componentTree);
        scenePanel.setGameObject(scene);
        if (scene != null) {

            loadGameObject(componentTree, scene);
            scene.load_system();
        }
    }

    private void loadGameObject(GameObjectComponentTree gameObjectComponentTree, GameObject gameObject)
    {
        if (gameObject != null)
        {
                gameObject.getChildren().forEach(objs -> {
                    System.out.println(objs);
                    GameObjectComponentTree item = new GameObjectComponentTree(objs);
                    gameObjectComponentTree.addTree(item);
                    loadGameObject(item, objs);

            } );
        }
    }
    @Override
    public void selectedItem(BaseComponentTree item) {
        ((BaseGameComponentTree) item).getScenePanel().drawing();
    }

}
