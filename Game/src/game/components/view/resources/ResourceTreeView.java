package game.components.view.resources;

import com.properties.components.BaseComponentTree;
import com.tree.TreeBase;
import com.tree.TreeViewController;
import game.components.tree.base.fileType;
import game.components.tree.resources.ResourceComponentTree;
import game.core.system.Icons;

import java.io.File;


public class ResourceTreeView extends TreeViewController {

    private ResourceComponentTree componentTree;

    public ResourceTreeView() {
        setTabView(TreeBase.newTab("Resource", this));
        setId("resourceTreeView");
    }

    @Override
    public void selectedItem(BaseComponentTree item) {
     //   ((BaseResourceComponentTree) item).getScenePanel().drawing();
    }

    public void load(File file)
    {
        componentTree = new ResourceComponentTree(this, file);
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
                ResourceComponentTree tree = new ResourceComponentTree(this, f);
                componentTree.addTree(tree);
                if (f.isDirectory())
                    loadResources(f, tree);

            }
        }
    }

    public ResourceComponentTree getComponentTree() {
        return componentTree;
    }
}
