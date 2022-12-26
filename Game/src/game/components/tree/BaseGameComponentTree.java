package game.components.tree;

import com.properties.components.BaseComponentTree;
import com.tree.TreeSceneController;
import game.core.objects.GameObject;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;

public abstract class BaseGameComponentTree extends BaseComponentTree {

    private GameObject gameObject;
    public BaseGameComponentTree(TreeSceneController controller, Image icon) {
        super(controller, icon);
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
}
