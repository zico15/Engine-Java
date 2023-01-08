package game.opengl.renderer;


import game.core.base.ImageBuffer;
import game.core.transforme.Vector2D;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;

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



    public void draw(Image image, float x, float y, float width, float height){
        image.bind();

        float scaleX = width / Window.width, scaleY = height  / Window.height;
        float c = 1;

        glBegin(GL11.GL_QUADS);
        glTexCoord2f(1,1);
        glVertex2f(-scaleX + x  - c, -scaleY + y + c);

        glTexCoord2f(0,1);
        glVertex2f(scaleX + x - c, -scaleY + y + c);

        glTexCoord2f(0,0);
        glVertex2f(scaleX + x - c, scaleY + y + c);

        glTexCoord2f(1,0);
        glVertex2f(-scaleX + x - c, scaleY + y + c);

        glEnd();
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
