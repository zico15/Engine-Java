package game.core.objects;


import game.core.base.BaseEvents;
import game.core.base.IRender;
import game.core.base.IStart;
import game.core.base.IUpdate;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Scene extends GameObject implements IStart, IRender, IUpdate {

    public final ArrayList<GameObject> objectsTemp = new ArrayList<>();

    private static int id = 1;

    public Scene() {
        super("Scene_" + id++);
        baseEvents = new BaseEvents();
    }

    public Scene(String name) {
        super(name);
        baseEvents = new BaseEvents();
    }

    @Override
    public void start() {
        addGameObject(new TileMaps());
    }


    @Override
    public void render(GraphicsContext graphics2D) {
        super.render(graphics2D);
        //baseEvents.render(graphics2D);
    }

    @Override
    public void update() {

    }


    @Override
    public String toString() {
        return "Scene{" + baseEvents + "}";
    }
}
