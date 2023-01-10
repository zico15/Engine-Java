package game.components.tree.base;

import com.properties.components.BaseComponentTree;
import com.tree.TreeViewController;
import game.components.tree.resources.ResourceComponentTree;
import game.project.GameEngine;
import game.project.prefabs.Prefab;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import java.io.File;

public abstract class BaseResourceComponentTree extends BaseComponentTree {

    private File file;

    private final Prefab prefab;

    public BaseResourceComponentTree(Prefab prefab, File file, TreeViewController controller, Image icon) {
        super(controller, icon);
        this.setFile(file);
        setContextMenu(creatingMenu());
        this.prefab = prefab;
    }

    @Override
    public void preview() {

    }


    public ContextMenu creatingMenu(){
        MenuItem addFolder = new MenuItem("New Folder");
        addFolder.setOnAction(e -> {
            TextInputDialog dialogoNome = new TextInputDialog();
            dialogoNome.setTitle("Folder");
            dialogoNome.setHeaderText("Entre com o nome do folder");
            dialogoNome.setContentText("Nome:");
            dialogoNome.showAndWait().ifPresent(v -> {
                if (v == null || v.isEmpty())
                    return;
                File f = new File(getFile(), v);
                if( f.mkdirs())
                    addTree(new ResourceComponentTree(null, getController(), f));
                System.out.println(f + " /  " + f.exists());
            });
        });
        MenuItem addFile = new MenuItem("New File");
        addFile.setOnAction(e -> {

        });
        return new ContextMenu(addFolder, addFile);
    }

    public String getSubFile() {
        String project = GameEngine.gameProject.getDirectory().getPath() + "/";
        return getFile().getPath().replace(project, "");
    }
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }


    public static fileType getExtensionType(File file) {
        if (file.isDirectory() && "Build".equals(file.getName()))
            return fileType.FOLDER_BUILD;
        if (file.isDirectory())
            return fileType.FOLDER_ANY;
        String name = file.getName();
        String extension = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1,name.length()) : name;
        extension = extension.trim().toLowerCase();
        System.out.println("extension: " + extension);
        switch (extension)
        {
            case "java": return fileType.FILE_JAVA;
            case "cpp": return fileType.FILE_CODE;
            case "scene": return fileType.FILE_SCENE;
            case "png": return fileType.FILE_IMAGE;
        }
        return  fileType.FILE_ANY;
    }


    public Prefab getPrefab() {
        return prefab;
    }
}
