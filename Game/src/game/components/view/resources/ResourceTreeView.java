package game.components.view.resources;

import com.properties.components.BaseComponentTree;
import com.tree.TreeBase;
import com.tree.TreeViewController;
import game.components.tree.resources.ProjectComponentTree;
import game.core.project.GameProject;

import java.io.File;


public class ResourceTreeView extends TreeViewController {

    private final ProjectComponentTree componentTree;

    public ResourceTreeView() {
        setTabView(TreeBase.newTab("Resource", this));
        setId("resourceTreeView");
        componentTree = new ProjectComponentTree(this);
        setRoot(getComponentTree());
    }

    @Override
    public void selectedItem(BaseComponentTree item) {
     //   ((BaseResourceComponentTree) item).getScenePanel().drawing();
    }

    public void load(File file)
    {
        System.out.println(getClass().getSimpleName() + ": load");
        componentTree.load(file);
    }

    public ProjectComponentTree getComponentTree() {
        return componentTree;
    }
}
