package game.core.components;


import game.opengl.renderer.Graphics2D;
import game.core.transforme.Vector2D;
import game.opengl.renderer.Image;


import java.io.File;

public class Sprite extends ComponentBase {

    private String filePath;

    private transient Image image;


    public Sprite() {
        image = null;
    }

    public Sprite(String filePath) {
        this.filePath = filePath;
        load(filePath);
    }


    @Override
    public void render(Graphics2D gc) {
        if (getImage() != null) {
            Vector2D v = getParent().getVector();
            gc.draw(getImage(), v.getX(), v.getY(), v.getWidth(), v.getHeight());
        }
    }

    public boolean load(String filePath) {
        this.filePath = filePath;
        if (filePath != null && !filePath.isEmpty() ) {
            try {
                image = new Image(filePath);
                System.out.println("image: " + image);
            } catch (Exception e) {
                image = null;
                this.filePath = null;
                System.err.println("Sprite");
            }
        }
        return false;
    }

    public final void load_system() {
        load(getFile());
    }



    public int getWidth() {
        return image != null ? (int) image.getWidth() : 0;
    }



    public int getHeight() {
        return image != null ? (int) image.getHeight() : 0;
    }

    public String getFile() {
        return filePath;
    }

    public Image getImage() {
        return image;
    }
}
