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
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
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
        ImageProperties imageProperties = new ImageProperties(GameObjectProperties.properties, sprite);
        imageProperties.setAction(e -> {
                imageViewProperties.setSprite(e.getSprite());
                System.out.println("action");
            });
        getScenePanel().setOnMouseReleased(e -> {
            int x = ((int) (e.getX() / 32) * 32), y = ((int) (e.getY() / 32) * 32);
            rectangle2D.setSize(x, y);
            if (e.getButton() == MouseButton.PRIMARY)
                tileMaps.setTile(x, y, x_sub.get(), y_sub.get());
            if (e.getButton() == MouseButton.SECONDARY)
                tileMaps.removeTile(x, y);
            System.out.println("canava :"+ rectangle2D);
            rectangle2D = null;
        });
        getScenePanel().setOnMousePressed(e -> {
            int x = ((int) (e.getX() / 32) * 32), y = ((int) (e.getY() / 32) * 32);
            rectangle2D = new Rectangle();
            rectangle2D.setLocation(x, y);
        });
        getScenePanel().setOnMouseMoved(e -> {
            System.out.println("setOnMouseMoved");
            if (rectangle2D != null)
            {
                int w = ((int) (e.getX() / 32) * 32), h = ((int) (e.getY() / 32) * 32);
                System.out.println(rectangle2D.x + " / " + rectangle2D.y + " / " + (w - rectangle2D.x )  + " / " + (h - rectangle2D.y));
                getScenePanel().getGraphicsContext2D().setStroke(Color.GREEN);
                getScenePanel().getGraphicsContext2D().strokeRect(rectangle2D.x, rectangle2D.y, w - rectangle2D.x, h - rectangle2D.y);
            }
        });
        GameObjectProperties.addItem(imageProperties);
        GameObjectProperties.addItem(imageViewProperties);
    }


    @Override
    public void unselect() {
        getScenePanel().setOnMousePressed(null);
        getScenePanel().setOnMouseReleased(null);
        getScenePanel().setOnMouseMoved(null);
        System.out.println("close");;
    }


}
