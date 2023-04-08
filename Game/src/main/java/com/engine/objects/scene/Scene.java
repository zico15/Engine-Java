package com.engine.objects.scene;

import com.almasb.fxgl.app.scene.SceneFactory;
import com.engine.graphics.Graphics;
import com.engine.objects.base.GameObject;
import com.engine.objects.interfaces.IObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Scene implements IObject {

    public static Scene SCENE;
    private final ArrayList<GameObject> objects = new ArrayList<>();

    private final ArrayList<GameObject> objectsRemove = new ArrayList<>();

    public Scene(){
        SCENE = this;
    }
    public void add(GameObject object)
    {
        objects.add(object);
    }

    public void remove(GameObject object)
    {
        objectsRemove.add(object);
    }
    @Override
    public void onRender(Graphics gc) {

        Collections.sort(objects, Comparator.comparingInt(o -> (int) o.rectangle.getY()));
        objects.forEach(ob -> ob.onRender(gc));

        if (!objectsRemove.isEmpty())
        {
            objectsRemove.forEach(e -> objects.remove(e));
            objectsRemove.clear();
        }
    }

    @Override
    public void onUpdate(double tpf) {
        objects.forEach(ob -> ob.onUpdate(tpf));
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
