package game.project.scenes;


import game.core.components.GameObjectScript;
import game.core.components.Sprite;
import game.core.objects.*;
import game.core.transforme.*;

public class Scene_1 extends Scene {


    public Scene_1() {
        setVector(new Vector2D(0,0, 0, 0));
        init_game_objects();
    }
    private void init_game_objects() {
        {
            GameObject ob_1 = new GameObject("gameObject_1");
            ob_1.addComponent(new GameObjectScript());
            ob_1.setVector(new Vector2D(0,0, 200, 200));
            ob_1.addComponent(new Sprite("assets/10634-87037d9181da4d4a53968335f69df53f.jpg"));
            this.addGameObject(ob_1);
        }
    }

}