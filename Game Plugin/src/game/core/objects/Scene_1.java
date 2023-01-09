package game.core.objects;

import game.core.components.*;
import game.core.transforme.*;
import java.util.List;

public class Scene_1 extends Scene   {


    public  Scene_1() {
        setVector(new Vector2D(0,0, 0, 0));
        init_game_objects();
    }
    private void init_game_objects() {
        /*{
            GameObject ob_1 = new GameObject("gameObject_1");
            ob_1.setVector(new Vector2D(200,200, 100, 100));
            ob_1.addComponent(new Sprite("C:\Users\carlo\Pictures\thumb-1920-68954.jpg"));
            this.addGameObject(ob_1);
        }
        {
            TileMaps ob_1 = new TileMaps("tileMaps_1");
            ob_1.setVector(new Vector2D(0,0, 0, 0));
            ob_1.addComponent(new Sprite("C:\Users\carlo\Pictures\Kgw6C.png"));
            ob_1.getTiles().addAll(List.of(new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128), new TileMaps.Tile(0,0,32,128)));
            this.addGameObject(ob_1);
        }*/
    }

}