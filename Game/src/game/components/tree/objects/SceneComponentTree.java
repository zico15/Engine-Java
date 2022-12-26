package game.components.tree.objects;

import com.tree.TreeViewController;
import game.components.tree.base.BaseGameComponentTree;
import game.components.tree.base.fileType;
import game.components.view.objects.GameObjectProperties;
import game.components.view.objects.ScenePanel;
import game.core.objects.Scene;
import game.lib.Icons;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import java.io.IOException;

public class SceneComponentTree extends BaseGameComponentTree {

    private static int id = 1;

    public SceneComponentTree(TreeViewController controller, ScenePanel scenePanel) {
        super(controller, Icons.get(fileType.FILE_SCENE), scenePanel);
        setGameObject(new Scene("Scene_" + id++));
        setValue(getGameObject().getName());
        scenePanel.setGameObject(getGameObject());
    }

    @Override
    public void preview() {
        getScenePanel().setGameObject(getGameObject());
        GameObjectProperties.load(this, false);

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
        return new ContextMenu(addobject, addscript);
    }



}
