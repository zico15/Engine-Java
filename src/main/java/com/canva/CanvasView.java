package com.canva;

import com.project.Project;
import com.system.ImageBase;
import engine2d.base.ImageBuffer;
import engine2d.objects.Scene;
import engine2d.render.Graphics2D;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CanvasView extends Canvas {

    public AnimationTimer loop;
    public boolean         isRun = false;
    private final Graphics2DGame graphics2DGame;

    public CanvasView() {
        super(800,600);
        graphics2DGame = new Graphics2DGame(getGraphicsContext2D());
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
        if (Project.getScene() != null)
            Project.getScene().render(graphics2DGame);
    }

    private class Graphics2DGame extends  engine2d.render.Graphics2D{

        private final GraphicsContext  context;

        public Graphics2DGame(GraphicsContext  context){
            this.context = context;
        }
        @Override
        public void drawImage(ImageBuffer image, int x1, int y1) {
            if (image == null)
                return;
            WritableImage temp = new WritableImage(image.getWidth(), image.getHeight());
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++)
                    temp.getPixelWriter().setArgb(x, y, image.getArgb(x, y));
            }
            context.drawImage(temp, x1, y1);
        }


    }


}
