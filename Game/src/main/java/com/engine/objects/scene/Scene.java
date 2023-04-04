package com.engine.objects.scene;

import com.almasb.fxgl.app.scene.SceneFactory;
import com.engine.graphics.Graphics;
import com.engine.objects.interfaces.IObject;

import java.util.ArrayList;

public class Scene implements IObject {

    private final ArrayList<IObject> objects = new ArrayList<>();

    public void add(IObject object)
    {
        objects.add(object);
    }
    @Override
    public void onRender(Graphics gc) {
        objects.forEach(ob -> ob.onRender(gc));
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
