package game.opengl.engine;


import game.opengl.listener.events.Event;
import game.opengl.listener.KeyListener;
import game.opengl.renderer.Window;
import game.project.scenes.Scene_2;

public class Main {
    public static void main(String[] args) {

        Window window = new Window("Game");
        Scene_2 scene = new Scene_2();
        /*GameObject gameObject = new GameObject();
        gameObject.setVector(new Vector2D(0,0, 100, 100));
        gameObject.addComponent(new Sprite("assets/tes.png"));
        scene.addGameObject(gameObject);*/
        window.setScene(scene);
        Event.addEventHandler(KeyListener.KEY_PRESSED, e -> {

        } );
        window.run();
    }
}