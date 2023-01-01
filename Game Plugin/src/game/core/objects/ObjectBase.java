package game.core.objects;

import game.core.base.BaseEvents;
import game.core.components.ComponentBase;
import game.core.transforme.Vector2D;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ObjectBase implements Serializable {

    private final List<GameObject> children = new ArrayList<>();
    private final List<ComponentBase> components = new ArrayList<>();
    public BaseEvents baseEvents;
    private Vector2D vector = new Vector2D();
    private String name;
    private GameObject parent;

    private String packageName;

    public ObjectBase() {
    }

    public ObjectBase(String name) {
        this.setName(name);
    }


    public final boolean removeObject(GameObject ob) {
        baseEvents.removeGameObjectEvent(ob);
        return children.remove(ob);
    }

    public final void removeAll() {
        children.forEach(o -> baseEvents.removeGameObjectEvent(o));
        children.clear();
    }

    public final ObjectBase getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    public final List<GameObject> getChildren() {
        return children;
    }

    public final List<ComponentBase> getComponents() {
        return components;
    }

    public final ComponentBase getComponent(String type) {
        int size = getComponents().size();
        for (int i = 0; i < size; i++) {
            if (getComponents().get(i).getType() == type)
                return getComponents().get(i);
        }
        return null;
    }

    public final <T> ComponentBase getComponent(Class<T> type) {
        int size = getComponents().size();
        for (int i = 0; i < size; i++) {
            if (getComponents().get(i).getClass().equals(type))
                return getComponents().get(i);
        }
        return null;
    }

    public final boolean addComponent(ComponentBase component) {
        if (component == null)
            return false;
        component.setParent(this);
        return components.add(component);
    }

    public final boolean removeComponent(ComponentBase component) {
        return components.remove(component);
    }

    protected void start_system() {

    }


    public final String getType() {
        return getClass().getSimpleName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector2D getVector() {
        return vector;
    }

    public void setVector(Vector2D vector) {
        this.vector = vector;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPackage(){
        return packageName == null ? getName() : packageName + "/" + getName();
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
