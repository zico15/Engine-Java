package game.components.tree.objects;

import game.components.properties.ImageProperties;
import game.components.properties.ImageViewProperties;
import game.components.view.objects.GameObjectProperties;
import game.core.components.Sprite;
import game.core.objects.TileMaps;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TileMapsComponentTree extends GameObjectComponentTree {


    private Rectangle rectangle2D = null;
    public TileMapsComponentTree(TileMaps tileMaps) {
        super(tileMaps);
    }

    @Override
    public void preview() {
        TileMaps tileMaps = (TileMaps) getGameObject();
        GameObjectProperties.load(this, false);
        Sprite sprite = (Sprite) getGameObject().getComponent(Sprite.class);
        System.out.println(getGameObject().getComponents().size() + " sprite: " + (sprite != null) + " " + sprite );
        ImageViewProperties imageViewProperties = new ImageViewProperties(GameObjectProperties.properties, sprite);
        AtomicInteger x_sub = new AtomicInteger();
        AtomicInteger y_sub = new AtomicInteger();
        imageViewProperties.getCanvas().setOnMouseClicked(e -> {
            imageViewProperties.drawSprite();
            imageViewProperties.getGraphicsContext2D().setStroke(Color.GREEN);
            x_sub.set(((int) (e.getX() / 32) * 32));
            y_sub.set(((int) (e.getY() / 32) * 32));
            imageViewProperties.getGraphicsContext2D().strokeRect(x_sub.get(), y_sub.get(), 32, 32);
        });
        ImageProperties imageProperties = new ImageProperties(sprite);
        imageProperties.setAction(e -> {
                imageViewProperties.setSprite(e.getSprite());
                System.out.println("action");
            });
        getScenePanel().setOnMouseReleased(e -> {
            System.out.println("rectangle2D: " + rectangle2D != null);
            rectangle2D =  correction();
            for (int x = rectangle2D.x; x < rectangle2D.width; x += tileMaps.getGrid())
            {
                for (int y = rectangle2D.y; y < rectangle2D.height; y += tileMaps.getGrid())
                {
                    if (e.getButton() == MouseButton.PRIMARY)
                        tileMaps.setTile(x, y, x_sub.get(), y_sub.get());
                    if (e.getButton() == MouseButton.SECONDARY)
                        tileMaps.removeTile(x, y);
                }
            }
            System.out.println("canava :"+ rectangle2D);
            rectangle2D = null;
            getScenePanel().getAnimationTimer().start();
        });
       /* getScenePanel().setOnMouseClicked(e -> {
            int x = ((int) (e.getX() / 32) * 32), y = ((int) (e.getY() / 32) * 32);
            rectangle2D = new Rectangle();
            rectangle2D.setLocation(x, y);
            getScenePanel().getAnimationTimer().stop();
        });*/
        getScenePanel().setOnMousePressed(e -> {
            int x = ((int) (e.getX() / 32) * 32), y = ((int) (e.getY() / 32) * 32);
            rectangle2D = new Rectangle();
            rectangle2D.setLocation(x, y);
            getScenePanel().getAnimationTimer().stop();
        });
        getScenePanel().setOnMouseDragged(e -> {
            if (rectangle2D != null)
            {
                int x = ((int) (e.getX() / 32) * 32) + 32, y = ((int) (e.getY() / 32) * 32) + 32;
                rectangle2D.setSize(x, y);
                getScenePanel().drawing();
                correction();
            }
        });

        GameObjectProperties.addItem(imageProperties.getView());
        GameObjectProperties.addItem(imageViewProperties);
    }

    private Rectangle correction(){
        int x = rectangle2D.x < rectangle2D.width ? rectangle2D.x : rectangle2D.width;
        int y = rectangle2D.y < rectangle2D.height ?  rectangle2D.y : rectangle2D.height;
        int w = rectangle2D.x < rectangle2D.width ? rectangle2D.width : rectangle2D.x;
        int h = rectangle2D.y < rectangle2D.height ?  rectangle2D.height : rectangle2D.y;
        getScenePanel().getGraphicsContext2D().setStroke(Color.GREEN);
        getScenePanel().getGraphicsContext2D().strokeRect(x, y, (w - x), (h - y));
        return  new  Rectangle(x, y, w, h);
    }

    @Override
    public void unselect() {
        getScenePanel().setOnMouseClicked(null);
        getScenePanel().setOnMousePressed(null);
        getScenePanel().setOnMouseReleased(null);
        getScenePanel().setOnMouseDragged(null);
        System.out.println("unselect");
    }


}
