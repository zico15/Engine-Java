package game.components.tree.objects;

import game.components.tree.base.BaseGameComponentTree;
import game.components.tree.base.fileType;
import game.components.view.objects.GameObjectProperties;
import game.core.objects.GameObject;
import game.core.objects.Scene;
import game.core.objects.TileMaps;
import game.core.system.Icons;
import game.project.GameEngine;
import game.project.GameProject;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class GameObjectComponentTree extends BaseGameComponentTree {




    public GameObjectComponentTree(GameObject gameObject) {
        super(GameEngine.sceneTreeView, null, GameEngine.scene);
        setGameObject(gameObject);
        setValue(getGameObject().getName());
        setType(getType(gameObject));
        setIcon(Icons.get(getType()));
    }

    @Override
    public void preview() {
        GameObjectProperties.load(this);
        System.out.println(getClass().getSimpleName());
    }

    @Override
    public ContextMenu creatingMenu() {
        MenuItem addGameObject = new MenuItem("GameObject");
        addGameObject.setOnAction(e -> newGameObject(new GameObjectComponentTree(new GameObject())));
        /*MenuItem addscript = new MenuItem("New script");
        addscript.setOnAction(e -> {

        });*/
        MenuItem addTileMaps = new MenuItem("TileMaps");
        addTileMaps.setOnAction(e -> newGameObject(new TileMapsComponentTree(new TileMaps())));
        return new ContextMenu(addGameObject, addTileMaps);
    }

    public final void newGameObject(GameObjectComponentTree gameObjectComponentTree) {
        addTree(gameObjectComponentTree);
        getGameObject().addGameObject(gameObjectComponentTree.getGameObject());
    }

    public static fileType getType(GameObject gameObject)
    {
        if (gameObject instanceof Scene)
            return (fileType.FILE_SCENE);
        else if (gameObject instanceof TileMaps)
            return (fileType.FILE_TILE_MAPS);
        return (fileType.FILE_OBJECT);
    }

    /****
     * create a GameObjectComponentTree from the gameObject type
     * **/
    public static GameObjectComponentTree newGameObjectComponentTree(GameObject gameObject) {

        switch (getType(gameObject))
        {
            case FILE_SCENE -> {
                return new SceneComponentTree((Scene) gameObject);
            }
            case FILE_TILE_MAPS -> {
                return new TileMapsComponentTree((TileMaps) gameObject);
            }
        }
       return new GameObjectComponentTree(gameObject);
    }

}
