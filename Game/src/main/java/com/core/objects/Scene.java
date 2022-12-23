package com.core.objects;


import com.core.base.BaseEvents;
import com.core.base.IRender;
import com.core.base.IStart;
import com.core.base.IUpdate;
import com.core.render.Graphics2D;

import java.util.ArrayList;

public class Scene extends GameObject implements IStart, IRender, IUpdate {

    public final ArrayList<GameObject> objectsTemp = new ArrayList<>();


    public Scene() {
        super("scene_01");
        baseEvents = new BaseEvents();
        System.out.println("Scene");
    }

    public Scene(String name) {
        super(name);
        System.out.println("Scene");
    }

    @Override
    public void start() {

    }


    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
        baseEvents.render(graphics2D);
    }

    @Override
    public void update() {

    }

    @Override
    public GameObject addGameObject(GameObject ob) {
        objectsTemp.add(ob);
        return ob;
    }

    @Override
    public String toString() {
        return "Scene{" + baseEvents + "}";
    }
}
