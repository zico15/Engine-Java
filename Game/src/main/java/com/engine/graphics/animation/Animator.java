package com.engine.graphics.animation;

import com.engine.graphics.Graphics;
import com.engine.system.GameLoop;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;

public class Animator {

    private Image img;

    private double speed;

    private double index;

    private int index_stop_frame;

    private boolean isLoop;
    private boolean isActive;

    private boolean endFrame;
    private ArrayList<Rectangle> frames = new ArrayList<>();

    public Animator(Image img, double speed, boolean isLoop){
        this.img = img;
        this.speed = speed;
        this.isLoop = isLoop;
        this.index_stop_frame = 0;
    }

    public void addFrame(int sx, int sy, int sw, int sh)
    {
        frames.add(new Rectangle(sx, sy, sw, sh));
    }

    public void addFrames(int sx, int sy, int qw, int size_w, int  size_h)
    {
            for (int x = 0; x < qw;  x++)
                addFrame(x * size_w, sy, size_w, size_h);
            this.index_stop_frame = 0;
    }

    public void addFrames(int sx, int sy, int qw, int size_w, int  size_h, int index_stop_frame){
        addFrames(sx, sy, qw, size_w, size_h);
        this.index_stop_frame = index_stop_frame;
    }

    public void play(){
        this.isActive = true;
    }
    public void setActive(boolean active)
    {
        this.isActive = active;
        this.index = 0;
        if (index_stop_frame < 0 || index_stop_frame >= frames.size())
            index_stop_frame = 0;
    }

    public void draw(Graphics gc, double x, double y, double dw, double dh){
        if (isActive) {
            gc.drawImage(img, frames.get((int) index), x, y, dw, dh);
            index += (speed * GameLoop.TPF);
            if (index >= frames.size()) {
                index = 0;
                isActive = isLoop;
            }
        }else
            gc.drawImage(img, frames.get(index_stop_frame), x, y, dw, dh);
    }

}
