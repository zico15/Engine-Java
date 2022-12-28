package game.core.project;

import com.project.Project;
import com.system.FileSystem;
import game.components.tree.base.BaseResourceComponentTree;
import game.components.tree.base.fileType;
import game.core.objects.Scene;

import java.io.File;
import java.util.ArrayList;

import game.core.system.FileSystemGame;
import game.core.system.GameEngine;


public class GameProject extends Project {


    private Scene sceneSelect;
    private final ArrayList<Scene> scenes = new ArrayList<>();


    @Override
    public void load(File file)
    {
        GameEngine.resourceTreeView.load(file);
        scenes.clear();
        setDirectory(file);
        loadScenes(file);
        if (scenes.size() == 0)
            scenes.add(new Scene());
        sceneSelect = scenes.get(0);
        GameEngine.sceneTreeView.load(sceneSelect);
    }

    private void loadScenes(File file)
    {
        if (file.isDirectory())
        {
            for(File f :  file.listFiles())
            {
                if (f.isDirectory())
                    loadScenes(f);
                else if (BaseResourceComponentTree.getExtensionType(f) == fileType.FILE_SCENE)
                {
                    try {
                        Object o = FileSystemGame.readGameObject(f);
                        if (o instanceof Scene)
                            scenes.add((Scene) o);
                        System.out.println(f);
                    }catch (Exception e)
                    {
                        System.err.println(f);
                    }
                }
            }
        }
    }

    @Override
    public void save() {
        System.out.println("project: "  + getDirectory());
        if (getDirectory() != null && getDirectory().exists()) {
            System.out.println("save project");
            scenes.forEach(scene -> {
                FileSystemGame.writeGameObject(new File(getDirectory(), (scene.getPackage() + ".scene")), scene);
            });
        }
        GameEngine.resourceTreeView.load(getDirectory());
    }

    public ArrayList<Scene> getScenes() {
        return scenes;
    }

    public Scene getSceneSelect() {
        return sceneSelect;
    }

    public void setSceneSelect(Scene sceneSelect) {
        this.sceneSelect = sceneSelect;
    }
}
