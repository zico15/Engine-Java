package game.core.base;

import game.opengl.renderer.Graphics2D;

import java.io.Serializable;

public interface BaseStructure extends Serializable {

    void start();

    void render(Graphics2D graphics2D);

    void update();

    String getType();

}
