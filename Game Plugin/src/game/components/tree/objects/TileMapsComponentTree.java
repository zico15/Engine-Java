package game.components.tree.objects;

import com.almasb.fxgl.core.collection.Array;
import game.components.properties.ImageProperties;
import game.components.properties.ImageViewProperties;
import game.components.tree.base.fileType;
import game.components.view.objects.GameObjectProperties;
import game.core.components.Sprite;
import game.core.objects.Scene;
import game.core.objects.TileMaps;
import game.core.system.Icons;

public class TileMapsComponentTree extends GameObjectComponentTree {



    public TileMapsComponentTree(TileMaps tileMaps) {
        super(tileMaps);
    }

    @Override
    public void preview() {
        GameObjectProperties.load(this, false);
        Sprite sprite = (Sprite) getGameObject().getComponent(Sprite.class);
        System.out.println(getGameObject().getComponents().size() + " sprite: " + (sprite != null) + " " + sprite );
        GameObjectProperties.addItem(new ImageViewProperties(null, sprite));
        System.out.println("Components: " + getGameObject().getComponents().size());
        Array<String> a = new Array<>();
        a.forEach();
    }


}
