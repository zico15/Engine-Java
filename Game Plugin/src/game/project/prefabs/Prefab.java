package game.project.prefabs;


import game.components.tree.resources.ResourceComponentTree;

import java.io.Serializable;

public abstract class Prefab<T> implements Serializable {

    private String path;
    private final T object;

    private transient ResourceComponentTree resourceComponentTree;
    public Prefab(T object) {
        this.object = object;
    }

    public Prefab(String path, T object) {
        this.setPath(path);
        this.object = object;
    }

    public abstract void save();

    public abstract void load();

    public abstract void setName(String name);

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public T getObject() {
        return object;
    }

    public void preview() {
        System.out.println("Prefab: " + getClass().getSimpleName());
    }

    @Override
    public String toString() {
        return "Prefab{" +
                "path='" + path + '\'' +
                ", object=" + object +
                '}';
    }

    public ResourceComponentTree getResourceComponentTree() {
        return resourceComponentTree;
    }

    public void setResourceComponentTree(ResourceComponentTree resourceComponentTree) {
        this.resourceComponentTree = resourceComponentTree;
    }
}
