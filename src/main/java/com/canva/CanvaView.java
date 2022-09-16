package com.canva;

import engine2d.objects.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CanvaView extends Canvas {

    private final GraphicsContext gc;
    private final CanvaGame   canvaGame;
    public Scene  scene;

    public CanvaView() {
        super(800, 600);
        gc = getGraphicsContext2D();
        canvaGame = new CanvaGame(gc);
    }

    public void drawLines() {

        if (scene == null)
            return;
        scene.render(canvaGame);
        System.out.println("CanvaGame");
    }
}
