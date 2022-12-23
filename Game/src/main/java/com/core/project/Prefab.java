package com.core.project;



import com.core.objects.GameObject;
import com.core.system.FileController;

import java.io.File;
import java.io.Serializable;

public class Prefab implements Serializable {

    private String path, name;
    private transient GameObject gameObject;

    public Prefab() {
    }

    public Prefab(String path, GameObject gameObject) {
        this.setGameObject(gameObject);
        this.setPath(path);
    }

    public void save(File file) {
        File dir = new File(file, getPath());
        if (!dir.exists())
            dir.mkdirs();
        System.out.println("Prefab: " + new File(dir, name));
        FileController.save(new File(dir, name + ".pref"), this);
    }

    public void load(File file) {
        this.setGameObject((GameObject) FileController.read(new File(file, getPath())));
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
        if (gameObject != null)
            name = gameObject.getName();
    }
}
