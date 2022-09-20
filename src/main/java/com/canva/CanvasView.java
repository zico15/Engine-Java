package com.canva;

import com.system.ImageBase;
import engine2d.objects.Scene;
import engine2d.render.Graphics2D;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class CanvasView extends Graphics2D {

    public Scene  scene;
    public AnimationTimer loop;
    public boolean         isRun = false;

    public CanvasView() {
        super(800,600);
        loop = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                render();
            }
        };
        //widthProperty().addListener(evt -> draw());
        //heightProperty().addListener(evt -> draw());
    }

    private void draw() {
        double width = getWidth();
        double height = getHeight();

        getGraphics().clearRect(0, 0, width, height);
        getGraphics().setFill(Color.GRAY);
        getGraphics().fillRect(0,0,width, height);
    }
    public void render() {

        if (scene == null)
            return;
        getGraphics().clearRect(0,0,getWidth(), getHeight());
        scene.render(this);

    }




}
