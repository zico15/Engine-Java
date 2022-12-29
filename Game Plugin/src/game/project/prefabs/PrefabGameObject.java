package game.project.prefabs;

import game.components.tree.resources.ResourceComponentTree;
import game.core.objects.GameObject;
import game.core.system.FileSystemGame;
import game.core.system.GameEngine;
import game.project.GameProject;

import java.io.File;

public class PrefabGameObject extends Prefab<GameObject>{

    public PrefabGameObject(GameObject object) {
        super(object);
    }

    public PrefabGameObject(File file) {
        super(null);
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {
        setResourceComponentTree(new ResourceComponentTree(this, GameEngine.resourceTreeView, null));
    }

    @Override
    public void setName(String name) {
        getObject().setName(name);
    }


}
