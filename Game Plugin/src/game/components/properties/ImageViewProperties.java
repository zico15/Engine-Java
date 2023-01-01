package game.components.properties;

import game.components.view.objects.GameObjectProperties;
import game.core.components.Sprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;

import java.util.function.Consumer;

public class ImageViewProperties extends ScrollPane {

    private final GameObjectProperties properties;

    private Sprite sprite;
    private final Canvas canvas;

    private final GraphicsContext graphicsContext2D;

    private Consumer<GraphicsContext> action;

    public ImageViewProperties(GameObjectProperties properties, Sprite sprite) {
        this.properties = properties;
        canvas = new Canvas();
        graphicsContext2D = getCanvas().getGraphicsContext2D();
        this.setSprite(sprite);
        setContent(getCanvas());

    }


    public static MenuItem creatingProperties(GameObjectProperties properties) {
        MenuItem item = new MenuItem("Image View");
        item.setOnAction(e -> {

        });
        return item;
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void drawSprite(){
        getGraphicsContext2D().clearRect(0, 0 , getCanvas().getWidth(), getCanvas().getHeight());
        if (sprite != null && sprite.getImage() != null) {
            getCanvas().setWidth(sprite.getImage().getWidth());
            getCanvas().setHeight(sprite.getImage().getHeight());
            getGraphicsContext2D().drawImage(sprite.getImage(),  0, 0);
        }
        else {
            getCanvas().setWidth(0);
            getCanvas().setHeight(0);
        }
        if (action  != null)
            action.accept(getGraphicsContext2D());
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        drawSprite();
    }

    public void setAction(Consumer<GraphicsContext> action) {
        this.action = action;
    }

    public GraphicsContext getGraphicsContext2D() {
        return graphicsContext2D;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
