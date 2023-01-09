package game.core.transforme;

import java.io.Serializable;

public class Vector2D implements Serializable {

    private int x;
    private int y;
    private int width;
    private int height;

    public Vector2D() {
    }

    public Vector2D(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return  getX() + "," + getY() + ", " + getWidth() +", " + getHeight();
    }
}
