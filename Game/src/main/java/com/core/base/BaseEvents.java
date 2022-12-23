package com.core.base;


import com.core.objects.GameObject;
import com.core.render.Graphics2D;

import java.util.ArrayList;

public class BaseEvents {

    private final ArrayList<IStart> starts = new ArrayList<>();
    private final ArrayList<IRender> renders = new ArrayList<>();
    private final ArrayList<IUpdate> updates = new ArrayList<>();

    public static void addEvents(BaseEvents event, GameObject ob) {
        if (ob.baseEvents != event) {
            event.addGameObjectEvent(ob);
            ob.getChildren().forEach(o -> addEvents(event, o));
        }
    }

    public static void removeEvents(GameObject ob) {
        if (ob.baseEvents != null) {
            ob.baseEvents.removeGameObjectEvent(ob);
            ob.getChildren().forEach(o -> removeEvents(o));
        }
    }

    public boolean addGameObjectEvent(GameObject ob) {
        if (ob instanceof IStart)
            starts.add((IStart) ob);
        if (ob instanceof IRender)
            renders.add((IRender) ob);
        if (ob instanceof IUpdate)
            updates.add((IUpdate) ob);
        ob.baseEvents = this;
        return (true);
    }

    public boolean removeGameObjectEvent(GameObject ob) {
        if (ob instanceof IStart)
            starts.remove((IStart) ob);
        if (ob instanceof IRender)
            renders.remove((IRender) ob);
        if (ob instanceof IUpdate)
            updates.remove((IUpdate) ob);
        ob.baseEvents = null;
        return (true);
    }

    public void render(Graphics2D graphics2D) {
        ArrayList<IRender> a = (ArrayList<IRender>) renders.clone();
        a.forEach(ob -> ob.render(graphics2D));
    }

    @Override
    public String toString() {
        return "BaseEvents{" +
                "starts=" + starts +
                ", renders=" + renders +
                ", updates=" + updates +
                '}';
    }
}
