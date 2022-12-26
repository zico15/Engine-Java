package com.canva;


import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;

public class CanvasView extends Canvas {


    public AnimationTimer loop;
    public boolean isRun = false;

    public CanvasView() {
        super(800, 600);

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

        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());

    }


}
