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

    private transient Image image;


    public Sprite() {
        image = null;
    }

    public Sprite(File file) {
        this.file = file;
        load(file);
    }

    public Sprite(File file, int width, int height) {
        this(file);
    }

    @Override
    public void render(GraphicsContext gc) {
        if (getImage() != null) {
            Vector2D v = getParent().getVector();
            gc.drawImage(getImage(), v.getX(), v.getY(), v.getWidth(), v.getHeight());
        }
    }

    public boolean load(File file) {
        this.file = file;
        if (file != null && file.exists()) {
            try {
                image = new Image(new FileInputStream(file));
                return !getImage().isError();
            } catch (FileNotFoundException e) {
                image = null;
                this.file = null;
                System.err.println("Sprite");
            }
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
        return image != null ? (int) image.getWidth() : 0;
    }



    public int getHeight() {
        return image != null ? (int) image.getHeight() : 0;
    }

    public File getFile() {
        return file;
    }

    public Image getImage() {
        return image;
    }
}
