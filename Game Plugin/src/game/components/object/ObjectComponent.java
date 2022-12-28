package game.components.object;

import game.components.tree.objects.GameObjectComponentTree;
import game.components.tree.resources.ResourceComponentTree;
import game.core.objects.GameObject;
import game.core.system.GameEngine;

import java.io.File;

public class ObjectComponent {

    private String packageName;
    private final GameObject gameObject;

    private GameObjectComponentTree gameObjectComponentTree;

    private ResourceComponentTree resourceComponentTree;

    public ObjectComponent(GameObject gameObject){
        this.gameObject = gameObject;
        gameObjectComponentTree = new GameObjectComponentTree(GameEngine.sceneTreeView, GameEngine.scene);
        resourceComponentTree = new ResourceComponentTree(GameEngine.resourceTreeView, new File(GameEngine.gameProject.getDirectory(), gameObject.getPackage()));
    }
}
