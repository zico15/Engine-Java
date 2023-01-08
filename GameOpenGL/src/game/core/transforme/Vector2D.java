package game.core.transforme;

import java.io.Serializable;

public class Vector2D implements Serializable {

    public float x;

    public float y;
    private float width;
    private float height;

    public Vector2D() {
    }

    public Vector2D(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "x:" + getX() +
                ", y:" + getY() +
                ", w:" + getWidth() +
                ", h" + getHeight();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
