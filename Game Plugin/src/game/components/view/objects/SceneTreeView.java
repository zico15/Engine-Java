package game.components.view.objects;

import com.properties.components.BaseComponentTree;
import com.tree.TreeBase;
import com.tree.TreeViewController;
import game.components.tree.base.BaseGameComponentTree;
import game.components.tree.objects.SceneComponentTree;
import game.core.objects.Scene;


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
    }
    @Override
    public void selectedItem(BaseComponentTree item) {
        ((BaseGameComponentTree) item).getScenePanel().drawing();
    }

}
