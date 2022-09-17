package com.canva;

import engine2d.render.Canva;
import engine2d.transforme.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.util.HashMap;


public class CanvaGame extends Canva {

    private static final HashMap<BufferedImage, WritableImage> IMAGES = new HashMap<>();
    private final GraphicsContext gc;

    public CanvaGame(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void image(BufferedImage img, Vector2D v) {

        if (IMAGES.containsKey(img))
        {
            gc.drawImage(IMAGES.get(img), v.x, v.y, v.width, v.height);
            return;
        }
        System.out.println("image");
        WritableImage wr = null;
        if (img != null) {
            wr = new WritableImage(img.getWidth(), img.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    pw.setArgb(x, y, img.getRGB(x, y));
                }
            }
        }
        IMAGES.put(img, wr);
        gc.drawImage(wr, v.x, v.y, v.width, v.height);
    }

    /*
    public void image(Image img, Vector2D v) {
      /*  BufferedImage bf = new BufferedImage(v.width,v.height,BufferedImage.TYPE_INT_RGB);
        bf.getGraphics().drawImage(img, v.x, v.y, v.width, v.height, null);
        WritableImage wr = null;
        if (bf != null) {
            wr = new WritableImage(bf.getWidth(), bf.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < bf.getWidth(); x++) {
                for (int y = 0; y < bf.getHeight(); y++) {
                    pw.setArgb(x, y, bf.getRGB(x, y));
                }
            }
        }
        gc.drawImage(wr, v.x, v.y, v.width, v.height);
    }*/
}
