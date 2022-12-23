package com.core.objects;



import com.core.base.ImageBuffer;
import com.core.components.Sprite;
import com.core.render.Graphics2D;
import com.core.transforme.Vector2D;

import java.io.File;


public class TileMaps extends GameObject {

    protected ImageBuffer buffer;
    private Sprite sprite;

    public TileMaps() {
        this("tileMaps_1");
    }

    public TileMaps(String name) {
        super(name);
        vector = new UpdateVector();
    }

    @Override
    public void render(Graphics2D graphics2D) {
        if (getBuffer() != null)
            graphics2D.drawImage(buffer, 0, 0);
    }

    public void print(ImageBuffer image, Vector2D vector, int x1, int y1) {


        if (buffer == null || x1 + vector.getWidth() > buffer.getWidth() || y1 + vector.getHeight() > buffer.getWidth())
            return;
        System.out.println("vector: " + vector);
        for (int y = 0; y < vector.getHeight(); y++) {
            for (int x = 0; x < vector.getWidth(); x++) {
                buffer.setArgb(x1 + x, y1 + y, image.getArgb(x + vector.getX(), y + vector.getY()));
            }
        }
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(File file) {
        sprite = new Sprite(file);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }


    public ImageBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ImageBuffer buffer) {
        this.buffer = buffer;
    }

    private class UpdateVector extends Vector2D {

        private void update() {
            buffer = new ImageBuffer(getWidth(), getHeight());
        }

        @Override
        public void setHeight(int height) {
            if (height >= 0) {
                super.setHeight(height);
                update();
            }
        }

        @Override
        public void setWidth(int width) {
            if (width >= 0) {
                super.setWidth(width);
                update();
            }
        }
    }
}
