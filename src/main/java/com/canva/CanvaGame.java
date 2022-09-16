package com.canva;

import engine2d.render.Canva;
import engine2d.transforme.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class CanvaGame extends Canva {


    private final GraphicsContext gc;

    public CanvaGame(GraphicsContext gc) {
        this.gc = gc;
    }


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
        gc.drawImage(wr, v.x, v.y, v.width, v.height);*/
    }
}
