package com.canva;

import com.MainViewController;
import engine2d.objects.Scene;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class CanvaView extends Canvas {

    private AtomicReference<GraphicsContext> gc;
    private final CanvaGame   canvaGame;
    private Image image;
    public Scene  scene;
    public AnimationTimer loop;
    public boolean         isRun = false;

    public CanvaView() {

        setWidth(800);
        setHeight(800);
        gc = new AtomicReference<>(getGraphicsContext2D());
        canvaGame = new CanvaGame(gc.get());
        loop = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                render();
            }
        };
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
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
        gc.get().clearRect(0,0,getWidth(), getHeight());
        scene.render(canvaGame);
    }




}
