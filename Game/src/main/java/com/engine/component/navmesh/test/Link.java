package com.engine.component.navmesh.test;


public class Link {

    public enum Dir
    {
        TOP,
        DOWN,
        LEFT,
        RIGHT
    }

    public int x, y;
    private boolean top;
    private boolean down;
    private boolean left;
    private boolean right;

    public Link next;
    public Link preview;

    public int h;
    public int g;
    public int f;


    public Link(int x, int y, int g, Link preview){
        this.x = x;
        this.y = y;
        this.g = g;
        this.preview = preview;
    }
    public void setTop(boolean top){
        this.top = top;
    }

    public boolean isTop() {
        return top;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLink(boolean v, Dir dir)
    {
        switch (dir)
        {
            case TOP -> setTop(v);
            case DOWN -> setDown(v);
            case RIGHT -> setRight(v);
            case LEFT -> setLeft(v);
        }
    }
}
