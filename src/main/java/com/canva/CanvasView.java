package com.canva;

import com.project.Project;
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
        widthProperty().addListener(evt -> render());
        heightProperty().addListener(evt -> render());
    }

    public void render() {

        getGraphicsContext2D().clearRect(0,0,getWidth(), getHeight());
        if (Project.getScene() == null)
            return;
        Project.getScene().render(null);
    }




}
