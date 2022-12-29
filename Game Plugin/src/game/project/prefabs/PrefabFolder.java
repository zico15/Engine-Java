package game.project.prefabs;

import com.system.FileSystem;
import game.components.tree.objects.GameObjectComponentTree;
import game.components.tree.resources.ResourceComponentTree;

import java.io.File;

public class PrefabFolder extends Prefab<File>{




    private transient GameObjectComponentTree gameObjectComponentTree;

    public PrefabFolder(File file)
    {
        super(file);
    }
    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @Override
    public void setName(String name) {

        FileSystem.rename(getObject(), name);
    }
}
