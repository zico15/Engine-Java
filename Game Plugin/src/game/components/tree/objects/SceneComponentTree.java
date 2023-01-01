package game.components.tree.objects;

import game.components.tree.base.fileType;
import game.components.view.objects.GameObjectProperties;
import game.core.objects.Scene;
import game.core.system.Icons;

public class SceneComponentTree extends GameObjectComponentTree {

    private static int id = 1;

    public SceneComponentTree(Scene scene) {
        super(scene);
    }

    @Override
    public void preview() {
        getScenePanel().setScenes((Scene) getGameObject());
        GameObjectProperties.load(this, false);
    }


}
