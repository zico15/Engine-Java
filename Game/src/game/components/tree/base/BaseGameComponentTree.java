package game.components.tree.base;

import com.properties.components.BaseComponentTree;
import com.tree.TreeViewController;
import game.components.view.objects.ScenePanel;
import game.core.objects.GameObject;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;

public abstract class BaseGameComponentTree extends BaseComponentTree {

    private GameObject gameObject;

    private final ScenePanel scenePanel;

    public BaseGameComponentTree(TreeViewController controller, Image icon, ScenePanel scenePanel) {
        super(controller, icon);
        this.scenePanel = scenePanel;
        setContextMenu(creatingMenu());
    }

    @Override
    public void preview() {
        if (gameObject != null)
            System.out.println(gameObject.getName());
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public abstract ContextMenu creatingMenu();

    public ScenePanel getScenePanel() {
        return scenePanel;
    }
}