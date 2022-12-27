package game.core.project;

import com.project.Project;
import com.system.FileSistem;
import game.components.tree.base.BaseResourceComponentTree;
import game.components.tree.base.fileType;
import game.core.objects.Scene;

import java.io.File;
import java.util.ArrayList;

import game.core.system.GameEngine;


public class GameProject extends Project {


    private Scene sceneSelect;
    private final ArrayList<Scene> scenes = new ArrayList<>();


    @Override
    public void load(File file)
    {
        GameEngine.resourceTreeView.load(file);
        scenes.clear();
        sceneSelect = null;
        setDirectory(file);
        loadScenes(file);
        if (scenes.size() == 0)
            scenes.add(new Scene());
        GameEngine.sceneTreeView.load(scenes.get(0));
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
                    Object o = FileSistem.readObject(f);
                    if (o instanceof Scene)
                        scenes.add((Scene) o);
                }
            }
        }
    }

    @Override
    public void save() {
        System.out.println("project: "  + getDirectory());
        if (getDirectory() != null && getDirectory().exists()) {
            System.out.println("save project");
            scenes.forEach(scene -> FileSistem.saveObject(new File(getDirectory(), (scene.getName() + ".scene")), scene));
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
