package game.core.objects;

import game.core.components.Sprite;
import game.core.transforme.Vector2D;

import java.io.File;

public class Scene_1   extends Scene {

    public  Scene_1() {
        setVector(new Vector2D(0,0, 0, 0));
        init_game_objects();
    }
    public void init_game_objects() {
        {
            TileMaps ob_1 = new TileMaps("tileMaps_1");
            ob_1.setVector(new Vector2D(0,0, 0, 0));
            ob_1.addComponent(new Sprite(new File("C:\\Users\\carlo\\Pictures\\Kgw6C.png")));
            {
                TileMaps ob_2 = new TileMaps("tileMaps_2");
                ob_2.setVector(new Vector2D(0,0, 0, 0));
                ob_2.addComponent(new Sprite(new File("C:\\Users\\carlo\\Pictures\\10634-87037d9181da4d4a53968335f69df53f.jpg")));
                ob_1.addGameObject(ob_2);
            }
            this.addGameObject(ob_1);
        }
        {
            GameObject ob_1 = new GameObject("player");
            ob_1.setVector(new Vector2D(0,0, 0, 0));
            ob_1.addComponent(new Sprite(new File("C:\\Users\\carlo\\Pictures\\506ac28daf8e47af3f412e6d84d780b8.png")));
            {
                GameObject ob_2 = new GameObject("gameObject_1");
                ob_2.setVector(new Vector2D(0,0, 0, 0));
                ob_1.addGameObject(ob_2);
            }
            {
                GameObject ob_3 = new GameObject("gameObject_2");
                ob_3.setVector(new Vector2D(0,0, 0, 0));
                {
                    GameObject ob_4 = new GameObject("gameObject_4");
                    ob_4.setVector(new Vector2D(0,0, 0, 0));
                    {
                        GameObject ob_5 = new GameObject("gameObject_5");
                        ob_5.setVector(new Vector2D(0,0, 0, 0));
                        {
                            GameObject ob_6 = new GameObject("gameObject_6");
                            ob_6.setVector(new Vector2D(0,0, 0, 0));
                            ob_5.addGameObject(ob_6);
                        }
                        ob_4.addGameObject(ob_5);
                    }
                    ob_3.addGameObject(ob_4);
                }
                ob_1.addGameObject(ob_3);
            }
            {
                GameObject ob_7 = new GameObject("gameObject_3");
                ob_7.setVector(new Vector2D(0,0, 0, 0));
                ob_1.addGameObject(ob_7);
            }
            this.addGameObject(ob_1);
        }
    }

}