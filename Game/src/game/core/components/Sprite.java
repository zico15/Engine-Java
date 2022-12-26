package game.core.components;


import game.core.base.ImageBuffer;
import game.core.transforme.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sprite extends ComponentBase {

    private File file;
    private transient ImageBuffer buffer;

    private Image image;
    private int width;
    private int height;

    public Sprite() {
        image = null;
    }

    public Sprite(File file) {
        this.file = file;
        load(file);
    }

    public Sprite(File file, int width, int height) {
       this(file);
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (image != null) {
            Vector2D v = getParent().getVector();
            gc.drawImage(image, v.getX(), v.getY(), v.getWidth(), v.getHeight());
            System.out.println("render(GraphicsContext gc)");
        }
    }

    public boolean load(File file) {
        this.file = file;
        System.out.println("load Sprite: " + file.getPath());
        try {
            image = new Image(new FileInputStream(file));
           return !image.isError();
        } catch (FileNotFoundException e) {
            image = null;
            this.file = null;
            System.err.println("Sprite");
        }
        return false;
    }

    public final void load_system() {
        load(getFile());
    }

    public void setRotate(double rotation) {
        if (buffer != null)
            buffer.setRotate(rotation);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        if (buffer != null && width > 0)
            buffer.resize(width, height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        if (buffer != null && height > 0)
            buffer.resize(width, height);
    }

    public ImageBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ImageBuffer buffer) {
        this.buffer = buffer;
        if (getBuffer() != null) {
            this.width = getBuffer().getWidth();
            this.height = getBuffer().getHeight();
        }
    }

    public File getFile() {
        return file;
    }
}
