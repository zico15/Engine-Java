package game.components.tree.base;

import com.properties.components.BaseComponentTree;
import com.tree.TreeViewController;
import game.components.tree.resources.JavaResourceComponentTree;
import game.components.tree.resources.ResourceComponentTree;
import game.core.system.Icons;
import game.project.GameEngine;
import game.project.build.classBuild.ClassFileJavaEmpty;
import game.project.prefabs.Prefab;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import java.io.File;

import static game.core.system.FileSystemGame.getExtensionType;

public abstract class BaseResourceComponentTree extends BaseComponentTree {

    private File file;

    private final Prefab prefab;

    public BaseResourceComponentTree(Prefab prefab, File file, TreeViewController controller, Image icon) {
        super(controller, icon);
        this.setFile(file);
        setContextMenu(creatingMenu());
        this.prefab = prefab;
    }

    public BaseResourceComponentTree(File file, TreeViewController controller) {
        super(controller, Icons.get(getExtensionType(file)));
        this.setFile(file);
        setContextMenu(null);
        this.prefab = null;
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
        MenuItem addScript = new MenuItem("New Script");
        addScript.setOnAction(e -> {
            TextInputDialog dialogoNome = new TextInputDialog();
            dialogoNome.setTitle("Script");
            dialogoNome.setHeaderText("Entre com o nome do script");
            dialogoNome.setContentText("Nome:");
            dialogoNome.showAndWait().ifPresent(v -> {
                if (v == null || v.isEmpty())
                    return;
                String n =  v.contains(".java") ? v : v + ".java";
                File f = new File(getFile(), n);
                new ClassFileJavaEmpty(v, getPackage()).save(f);
                addTree(new JavaResourceComponentTree(null, getController(), f));
                System.out.println(f + " /  " + f.exists());
            });

        });
        return new ContextMenu(addFolder, addFile, addScript);
    }

    public String getSubFile() {
        String project = GameEngine.gameProject.getDirectory().getPath();
        return getFile().getPath().replace(project, "");
    }

    public String getPackage() {
        String project = getSubFile();
        if (project.isEmpty())
            return null;
        project = project.replaceAll("/", ".");
        if (project.charAt(0) == '.')
            project = project.substring(1, project.length());
        return project;
    }
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Prefab getPrefab() {
        return prefab;
    }
}
