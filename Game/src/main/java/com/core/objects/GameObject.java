package com.core.objects;


import com.core.base.BaseEvents;
import com.core.render.Graphics2D;

/**
 * https://stackoverflow.com/questions/11584444/java-opengl-draw-texture
 **/
public class GameObject extends ObjectBase {

    public GameObject() {
        super("gameObject_1");
    }

    public GameObject(String name) {
        super(name);
    }


    void render(Graphics2D graphics2D) {
        getComponents().forEach(c -> c.render(graphics2D));
    }


    public GameObject addGameObject(GameObject ob) {
        if (ob == null)
            return null;
        ob.setParent(this);
        getChildren().add(ob);
        BaseEvents.addEvents(baseEvents, ob);
        return ob;
    }

    protected final void load_system() {
        getComponents().forEach(c -> c.load_system());
        getChildren().forEach(c -> c.load_system());
    }
}
