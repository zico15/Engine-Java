package game.components.tree.objects;

import com.tree.TreeViewController;
import game.components.tree.base.BaseGameComponentTree;
import game.components.tree.base.fileType;
import game.components.view.objects.GameObjectProperties;
import game.components.view.objects.ScenePanel;
import game.core.objects.GameObject;
import game.core.system.Icons;
import game.project.GameEngine;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class GameObjectComponentTree extends BaseGameComponentTree {


    private static int id = 1;

    public GameObjectComponentTree(GameObject gameObject) {
        super(GameEngine.sceneTreeView, Icons.get(fileType.FILE_OBJECT), GameEngine.scene);
        setGameObject(gameObject);
        setValue(getGameObject().getName());
    }
    public GameObjectComponentTree(TreeViewController controller, ScenePanel scenePanel) {
        super(controller, Icons.get(fileType.FILE_OBJECT), scenePanel);
        setGameObject(new GameObject("GameObject_" + id++));
        setValue(getGameObject().getName());
    }

    @Override
    public void preview() {
        GameObjectProperties.load(this);
        System.out.println(getClass().getSimpleName());
    }

    @Override
    public ContextMenu creatingMenu() {
        MenuItem addobject = new MenuItem("New GameObject");
        addobject.setOnAction(e -> {
            GameObjectComponentTree gameObjectComponentTree = new GameObjectComponentTree(getController(), getScenePanel());
            addTree(gameObjectComponentTree);
            getGameObject().addGameObject(gameObjectComponentTree.getGameObject());
        });
        MenuItem addscript = new MenuItem("New script");
        addscript.setOnAction(e -> {

        });
        MenuItem addcomponent = new MenuItem("Components");
        addcomponent.setOnAction(e -> {

        });
        return new ContextMenu(addobject, addscript, addcomponent);
    }


}
