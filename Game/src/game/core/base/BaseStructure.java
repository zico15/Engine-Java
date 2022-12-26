package game.core.base;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public interface BaseStructure extends Serializable {

    void start();

    void render(GraphicsContext graphics2D);

    void update();

    String getType();

}
