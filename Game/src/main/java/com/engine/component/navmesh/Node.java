package com.engine.component.navmesh;


public class Node {


    public static final int TOP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int LEFT_TOP = 4;
    public static final int LEFT_DOWN = 5;
    public static final int RIGHT_TOP = 6;
    public static final int RIGHT_DOWN = 7;

    public double x, y;

    private double  speed;


    public Node preview;

    public int g;

    private int direction;

    public Node(double x, double y, int g, Node preview, int direction){
        this.x = x;
        this.y = y;
        this.g = g;
        this.preview = preview;
        this.direction = direction;
        this.speed = 10;
    }

    public void setDirection(int direction)
    {
          this.direction = direction ;
    }

    public int getDirection(){
           return  this.direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
