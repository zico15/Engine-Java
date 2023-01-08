package game.opengl.engine;


import game.core.components.Sprite;
import game.core.objects.GameObject;
import game.core.objects.Scene;
import game.core.transforme.Vector2D;
import game.opengl.listener.events.Event;
import game.opengl.listener.KeyListener;
import game.opengl.renderer.Window;

import static org.lwjgl.glfw.GLFW.*;

public class Main {
    public static void main(String[] args) {

        Window window = new Window("Game");
        Scene scene = new Scene();
        GameObject gameObject = new GameObject();
        gameObject.setVector(new Vector2D(0,0, 100, 100));
        gameObject.addComponent(new Sprite("assets/tes.png"));
        scene.addGameObject(gameObject);
        window.setScene(scene);
        Event.addEventHandler(KeyListener.KEY_PRESSED, e -> {
            switch (e.getKeyCode()){
                case GLFW_KEY_W -> gameObject.getVector().y += 0.01;
                case GLFW_KEY_S -> gameObject.getVector().y -= 0.01;
                case GLFW_KEY_D -> gameObject.getVector().x += 0.01;
                case GLFW_KEY_A -> gameObject.getVector().x -= 0.01;
            }
        } );
        window.run();
    }
}