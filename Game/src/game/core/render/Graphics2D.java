package game.core.render;


import game.core.base.ImageBuffer;

import java.awt.*;

public class Graphics2D {

    private final int width;
    private final int height;
    private final transient ImageBuffer buffer;

    public Graphics2D() {
        var dimension = Toolkit.getDefaultToolkit().getScreenSize();
        width = dimension.width;
        height = dimension.height;
        buffer = new ImageBuffer(getWidth(), getHeight());
    }

    public Graphics2D(int width, int height) {
        this.width = width;
        this.height = height;
        buffer = new ImageBuffer(width, height);
    }

    public void drawImage(ImageBuffer image, int x1, int y1) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++)
                buffer.setArgb(x + x1, y + y1, image.getArgb(x, y));
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
