package game.components.view.resources;

import com.tree.TreeViewController;
import game.components.tree.base.BaseResourceComponentTree;
import game.components.tree.base.fileType;
import game.core.system.FileSystemGame;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


public class ResourceTreeViewSelect extends TreeViewController {

    private ResourceComponentTreeSelect componentTree;

    protected ResourceComponentTreeSelect componentTreeSelect;

    private Consumer<ResourceComponentTreeSelect> selectConsumer;

    private   List<fileType> types;
    public void load(File file, Consumer<ResourceComponentTreeSelect> selectConsumer, fileType ...types)
    {
        this.selectConsumer =  selectConsumer;
        this.types = Arrays.stream(types).toList();
        setContextMenu(null);
        getSelectionModel().select(null);
        componentTree = new ResourceComponentTreeSelect( file, this);
        if (types.length > 0)
            loadResources(file, componentTree, this.getTypes());
        setRoot(componentTree);
        setFocused(false);
        setOnMouseReleased( e -> {
            if (componentTreeSelect != null) {
                System.out.println("MOUSE_RELEASED");
                if (componentTreeSelect.count > 2) {
                    System.out.println("select: " + componentTreeSelect.getFile());
                    componentTreeSelect.count = 0;
                    if (getSelectConsumer() != null)
                        getSelectConsumer().accept(componentTreeSelect);
                } else
                    componentTreeSelect.count++;
            }
        });
    }


    private void loadResources(File file, ResourceComponentTreeSelect componentTree,  List<fileType> types)
    {
        if (file.isDirectory() && !"Build".equals(file.getName()))
        {
            for (File f : file.listFiles())
            {
                ResourceComponentTreeSelect tree = new ResourceComponentTreeSelect(f, this);
                if (f.isDirectory() && !"Build".equals(file.getName()))
                {
                    loadResources(f, tree, types);
                    if (tree.getChildren().size() > 0)
                        componentTree.addTree(tree);
                } else if (types.contains(FileSystemGame.getExtensionType(f)))
                    componentTree.addTree(tree);
            }
        }
    }

    public Consumer<ResourceComponentTreeSelect> getSelectConsumer() {
        return selectConsumer;
    }


    public List<fileType> getTypes() {
        return types;
    }

    public class ResourceComponentTreeSelect extends BaseResourceComponentTree {

        private final fileType type;
        public int count;
        private ResourceTreeViewSelect controller;
        public ResourceComponentTreeSelect(File file, ResourceTreeViewSelect controller) {
            super( file, controller);
            setValue(file.getName());
            this.controller = controller;
            count = 0;
            type = FileSystemGame.getExtensionType(file);
        }

        @Override
        public void preview() {
            if (controller.componentTreeSelect != null)
                controller.componentTreeSelect.count = 0;
            count = 0;
            controller.componentTreeSelect = this;
            System.out.println("preview: " + count);
        }

        public fileType getType() {
            return type;
        }
    }
}
