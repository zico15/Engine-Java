package game.components.view.objects;

import com.properties.components.BaseComponentTree;
import com.tree.TreeBase;
import com.tree.TreeViewController;
import game.components.tree.base.BaseGameComponentTree;
import game.components.tree.objects.SceneComponentTree;


public class SceneTreeView extends TreeViewController {

    private final SceneComponentTree scene;

    public SceneTreeView(ScenePanel scenePanel) {
        setTabView(TreeBase.newTab("Scene", this));
        setId("sceneTreeView");
        scene = new SceneComponentTree(this, scenePanel);
        setRoot(scene);
    }

    @Override
    public void selectedItem(BaseComponentTree item) {
        ((BaseGameComponentTree) item).getScenePanel().drawing();
    }

}
