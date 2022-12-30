package game.core.objects;


import game.core.base.BaseEvents;
import javafx.scene.canvas.GraphicsContext;

/**
 * https://stackoverflow.com/questions/11584444/java-opengl-draw-texture
 **/
public class GameObject extends ObjectBase {

    private static int id = 1;
    public GameObject() {
        super("gameObject_" + id++);
    }

    public GameObject(String name) {
        super(name);
    }


    void render(GraphicsContext graphics2D) {
        System.out.println("GameObject");
        getComponents().forEach(c -> c.render(graphics2D));
        getChildren().forEach(c -> c.render(graphics2D));
    }

    public GameObject addGameObject(GameObject ob) {
        if (ob == null)
            return null;
        ob.setParent(this);
        getChildren().add(ob);
        BaseEvents.addEvents(baseEvents, ob);
        return ob;
    }

    public final void load_system() {
        getComponents().forEach(c -> c.load_system());
        getChildren().forEach(c -> c.load_system());
    }
}
