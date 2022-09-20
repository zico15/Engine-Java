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

public class CanvasView extends Canvas {

    private AtomicReference<GraphicsContext> gc;
    private final Graphics2D graphics2D;
    public Scene  scene;
    public AnimationTimer loop;
    public boolean         isRun = false;

    public CanvasView() {

        setWidth(800);
        setHeight(800);
        gc = new AtomicReference<>(getGraphicsContext2D());
        graphics2D = new Graphics2D((int) getWidth(), (int) getHeight());
        loop = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                render();
            }
        };
        //widthProperty().addListener(evt -> draw());
        //heightProperty().addListener(evt -> draw());
    }

    public GraphicsContext get()
    {
        return gc.get();
    }

    private void draw() {
        double width = getWidth();
        double height = getHeight();

        gc.get().clearRect(0, 0, width, height);
        gc.get().setFill(Color.GRAY);
        gc.get().fillRect(0,0,width, height);
    }
    public void render() {

        if (scene == null)
            return;
        System.out.println("clearRect");
        gc.get().clearRect(0,0,getWidth(), getHeight());
        scene.render(graphics2D);
        //gc.get().drawImage(ImageBase.convert(graphics2D.getBuffer()),0,0);
       // graphics2D.dispose();

    }




}
