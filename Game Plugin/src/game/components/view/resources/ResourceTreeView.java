package game.components.view.resources;

import com.properties.components.BaseComponentTree;
import com.system.FileSystem;
import com.tree.TreeBase;
import com.tree.TreeViewController;
import game.components.tree.base.BaseResourceComponentTree;
import game.components.tree.base.fileType;
import game.components.tree.resources.ResourceComponentTree;

import game.core.system.FileSystemGame;
import game.core.system.Icons;
import game.project.GameEngine;
import game.project.prefabs.Prefab;
import game.project.prefabs.PrefabFile;
import game.project.prefabs.PrefabFolder;
import game.project.prefabs.PrefabGameObject;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static game.components.tree.base.BaseResourceComponentTree.getExtensionType;


public class ResourceTreeView extends TreeViewController {

    private ResourceComponentTree componentTree;

    public ResourceTreeView() {
        setTabView(TreeBase.newTab("Resource", this));
        setId("resourceTreeView");
        setOnDragDetected(event -> {
            Dragboard db = startDragAndDrop(TransferMode.ANY);
            System.out.println("Circle 1 drag detected");
            /* Put a string on a dragboard */
            ClipboardContent content = new ClipboardContent();
            content.putString("detected");
            db.setContent(content);
            event.consume();
        });
        setOnDragOver(event -> {
            if (event.getDragboard().hasFiles() && getRoot() != null) {
                event.acceptTransferModes(TransferMode.ANY);
            }

            event.consume();
        });
        setOnDragDropped((DragEvent event) -> {
            if (getRoot() != null) {
                List<File> files = event.getDragboard().getFiles();
                files.forEach(f -> {

                    ResourceComponentTree item =  getSelectionModel().getSelectedItem() != null ? ((ResourceComponentTree)getSelectionModel().getSelectedItem()) : componentTree;

                    try {
                        File dest = new File(item.getFile(), f.getName());
                        FileSystem.copy(f, dest);
                        item.addTree(new ResourceComponentTree(null, this, dest));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(f);
                });
            }
            event.consume();
        });
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
