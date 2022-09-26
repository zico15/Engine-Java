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
import java.util.concurrent.atomic.AtomicReference;

public class CanvasView extends Canvas {

    public AnimationTimer loop;
    public boolean         isRun = false;
    private final Graphics2DGame graphics2DGame = new Graphics2DGame();

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
        if (Project.getScene() != null)
            Project.getScene().render(graphics2DGame);
        graphics2DGame.show(getGraphicsContext2D());
    }

    private class Graphics2DGame extends  engine2d.render.Graphics2D{

        private WritableImage writableImage = new WritableImage(1000,1000);
        private PixelWriter pixelWriter = writableImage.getPixelWriter();
        @Override
        public void drawImage(ImageBuffer image, int x1, int y1) {
            if (image == null || image.image == null)
                return;
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++)
                    pixelWriter.setArgb(x, y, image.getArgb(x, y));
            }
        }

        public void show(GraphicsContext  context){
            context.drawImage(writableImage, 0,0, 800,600);
        }
    }


}
