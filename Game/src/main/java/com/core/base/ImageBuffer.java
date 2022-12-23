package com.core.base;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class ImageBuffer implements Serializable {

    private BufferedImage image;

    private int width, height;


    public ImageBuffer(File file) {

        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageBuffer(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        this.width = width;
        this.height = height;
    }

    public ImageBuffer(File file, int width, int height) {
        this(file);
        resize(width, height);
    }


    public int getArgb(int x, int y) {
        return image.getRGB(x, y);
    }

    public void setArgb(int x, int y, int color) {
        image.setRGB(x, y, color);
    }

    public void setArgb(int x, int y, int a, int r, int g, int b) {
        image.setRGB(x, y,  (a << 24 | r << 16 | g << 8 | b));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setScale(float scaleX, float scaleY) {
        resize(width * scaleX, height * scaleY);
    }

    public void setScale(float scale) {
        setScale(scale, scale);
    }

    public void resize(float width, float height) {
        //image = image.
    }

    public void setRotate(double rotation) {

        resize(width, height);
    }
}
