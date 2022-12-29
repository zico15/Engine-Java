package game.project.prefabs;

import com.system.FileSystem;
import game.components.tree.objects.GameObjectComponentTree;

import java.io.File;

public class PrefabFile extends Prefab<File>{

    public PrefabFile(File file)
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
