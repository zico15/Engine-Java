package game.components.tree;

import com.properties.components.BaseComponentTree;
import com.system.FileSistem;
import com.tree.TreeItemResource;
import com.tree.TreeSceneController;
import game.components.view.GameObjectProperties;
import game.components.view.ScenePanel;
import game.core.objects.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class SceneComponentTree extends BaseGameComponentTree {

    private static Image icon;

    private final ScenePanel scenePanel;
    private  static int id = 1;


    static {
        try {
            icon = new Image(SceneComponentTree.class.getResource("/resources/icons/scene.png").openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SceneComponentTree(TreeSceneController controller, ScenePanel scenePanel) {
        super(controller, icon);
        setGameObject(new Scene("Scene_" + id++));
        setValue(getGameObject().getName());
        this.scenePanel = scenePanel;
        scenePanel.setGameObject(getGameObject());
    }

    @Override
    public void preview() {
        scenePanel.setGameObject(getGameObject());
        GameObjectProperties.load(this, false);

    }

    @Override
    public ContextMenu creatingMenu() {
        MenuItem addobject = new MenuItem("New GameObject");
        addobject.setOnAction(e -> {
            GameObjectComponentTree gameObjectComponentTree = new GameObjectComponentTree(getController());
            addTree(gameObjectComponentTree);
            getGameObject().addGameObject(gameObjectComponentTree.getGameObject());
        });
        MenuItem addscript = new MenuItem("New script");
        addscript.setOnAction(e -> {

        });
        return new ContextMenu(addobject, addscript);
    }


}
