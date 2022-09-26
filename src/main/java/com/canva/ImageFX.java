package com.canva;

import engine2d.base.ImageBuffer;
import engine2d.components.Sprite;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImageFX extends WritableImage{

    public  ImageFX(ImageBuffer image) {
        super(image.getWidth(), image.getHeight());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++)
                getPixelWriter().setArgb(x, y, image.getArgb(x, y));
        }
    }

    public  ImageFX(Sprite sprite) {
        super(sprite.getWidth(), sprite.getHeight());
        ImageBuffer image = sprite.getBuffer();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++)
                getPixelWriter().setArgb(x, y, image.getArgb(x, y));
        }
    }
}
