package game.components.tree;

import com.properties.components.BaseComponentTree;
import com.tree.TreeSceneController;
import game.components.view.GameObjectProperties;
import game.core.objects.GameObject;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;

import java.io.IOException;

public class GameObjectComponentTree extends BaseGameComponentTree {

    private static Image icon;

    private  static int id = 1;

    static {
        try {
            icon = new Image(GameObjectComponentTree.class.getResource("/resources/icons/object.png").openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GameObjectComponentTree(TreeSceneController controller) {
        super(controller, icon);
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
             GameObjectComponentTree gameObjectComponentTree = new GameObjectComponentTree(getController());
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
