package com.canva;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CanvaView extends Canvas {

    private final GraphicsContext gc;

    public CanvaView() {
        super(800, 600);
        gc = getGraphicsContext2D();
    }

    public void drawLines() {

        System.out.println("drawLines");
        gc.beginPath();
        gc.rect(0, 0, 800, 600);
        gc.moveTo(30.5, 30.5);
        gc.lineTo(150.5, 30.5);
        gc.lineTo(150.5, 150.5);
        gc.lineTo(30.5, 30.5);
        gc.stroke();
    }
}
