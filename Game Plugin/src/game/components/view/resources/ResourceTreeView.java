package game.components.view.resources;

import com.properties.components.BaseComponentTree;
import com.tree.TreeBase;
import com.tree.TreeViewController;
import game.components.tree.base.BaseResourceComponentTree;
import game.components.tree.base.fileType;
import game.components.tree.resources.ResourceComponentTree;

import game.core.system.FileSystemGame;
import game.core.system.Icons;
import game.project.prefabs.Prefab;
import game.project.prefabs.PrefabFile;
import game.project.prefabs.PrefabFolder;
import game.project.prefabs.PrefabGameObject;
import java.io.File;

import static game.components.tree.base.BaseResourceComponentTree.getExtensionType;


public class ResourceTreeView extends TreeViewController {

    private ResourceComponentTree componentTree;

    public ResourceTreeView() {
        setTabView(TreeBase.newTab("Resource", this));
        setId("resourceTreeView");
    }

    @Override
    public void selectedItem(BaseComponentTree item) {
        if (item != null){
            Prefab prefab =  ((BaseResourceComponentTree) item).getPrefab();
            if (prefab != null)
                prefab.preview();
        }
    }

    public void load(File file)
    {
        setContextMenu(null);
        getSelectionModel().select(null);
        componentTree = new ResourceComponentTree(new PrefabFolder(file), this, file);
        System.out.println(getClass().getSimpleName() + ": load");
        componentTree.load(file);
        componentTree.setIcon(Icons.get(fileType.FOLDER_ROOT));
        setRoot(getComponentTree());
        loadResources(file, getComponentTree());
    }

    private void loadResources(File file, ResourceComponentTree componentTree)
    {
        if (file.isDirectory())
        {
            for (File f : file.listFiles())
            {
                ResourceComponentTree tree = getResourceComponentType(getExtensionType(f), f);
                componentTree.addTree(tree);
                if (f.isDirectory())
                    loadResources(f, tree);
            }
        }
    }

    public ResourceComponentTree getComponentTree() {
        return componentTree;
    }

    public ResourceComponentTree getResourceComponentType(fileType type, File file)
    {
        switch (type)
        {
            case FILE_PREFAB -> {
                Prefab prefab = FileSystemGame.readPrefab(file);
                return (prefab.getResourceComponentTree());
            }
            case FOLDER_ANY -> {
                return (new ResourceComponentTree(new PrefabFolder(file), this, file));
            }
        }
        return new ResourceComponentTree(new PrefabFile(file), this, file);
    }
}
