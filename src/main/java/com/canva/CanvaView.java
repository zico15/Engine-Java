package com.canva;

import engine2d.objects.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class CanvaView extends Tip1ResizableCanvas {

    private final GraphicsContext gc;
    private final CanvaGame   canvaGame;
    private Image image;
    public Scene  scene;

    public CanvaView() {
        setWidth(800);
        setHeight(800);
        gc = getGraphicsContext2D();
        canvaGame = new CanvaGame(gc);
    }


    @Override
    public double computeAreaInScreen() {
        System.out.println("computeAreaInScreen: ");
        return super.computeAreaInScreen();
    }

    @Override
    public void resize(double v, double v1) {
        super.resize(v, v1);
        System.out.println("computeAreaInScreen: ");
    }

    public void render() {

        if (scene == null)
            return;
        gc.clearRect(0,0,getWidth(), getHeight());
        scene.render(canvaGame);
    }
}
