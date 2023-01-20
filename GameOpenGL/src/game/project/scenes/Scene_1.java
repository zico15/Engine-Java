package game.project.scenes;

import game.core.transforme.*;
import game.core.objects.*;


public class Scene_1 extends Scene   {



    public  Scene_1() {
        setVector(new Vector2D(0,0, 0, 0));
        init_game_objects();
    }
    private void init_game_objects() {
        {
            GameObject ob_1 = new GameObject("gameObject_1");
            ob_1.setVector(new Vector2D(0,0, 0, 0));
            this.addGameObject(ob_1);
        }
        {
            GameObject ob_1 = new GameObject("gameObject_2");
            ob_1.setVector(new Vector2D(0,0, 0, 0));
            this.addGameObject(ob_1);
        }
    }

}