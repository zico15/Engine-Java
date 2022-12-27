package game.core.base;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class ImageBuffer2 implements Serializable {

    private int[] pixels;

    private int[] pixels_origin;
    private int width, height;
    private int width_origin, height_origin;

    public ImageBuffer2(File file) {
        int[] pixels1 = null;
        try {
            BufferedImage image = ImageIO.read(file);
            pixels1 = load(image, image.getWidth(), image.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pixels = pixels1;
        pixels_origin = pixels1;
    }

    public ImageBuffer2(int width, int height) {
        pixels = new int[width * height];
        this.width = width;
        this.height = height;
        width_origin = width;
        height_origin = height;
        pixels_origin = pixels;
    }

    public ImageBuffer2(File file, int width, int height) {
        this(file);
        resize(width, height);
    }

    public final int[] load(BufferedImage image, int width, int height) {
        pixels = new int[width * height];
        this.width = width;
        this.height = height;
        width_origin = width;
        height_origin = height;
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        pixels_origin = pixels;
        return pixels;
    }

    public int getArgb(int x, int y) {
        return pixels[y * width + x];
    }

    public void setArgb(int x, int y, int color) {
        pixels[y * width + x] = color;
    }

    public void setArgb(int x, int y, int a, int r, int g, int b) {
        pixels[y * width + x] = (a << 24 | r << 16 | g << 8 | b);
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
        float tempX, tempY = 0;
        float resizeX = ((float) width_origin / width);
        float resizeY = ((float) height_origin / height);
        int[] pixels1 = new int[(int) width * (int) height];
        for (int y = 0; y < (int) height; y++) {
            tempX = 0;
            for (int x = 0; x < (int) width; x++) {
                pixels1[(y * (int) width + x)] = pixels_origin[(int) tempY * width_origin + (int) tempX];
                tempX += resizeX;
            }
            tempY += resizeY;
        }
        this.width = (int) width;
        this.height = (int) height;
        pixels = pixels1;
    }

    public void setRotate(double rotation) {

        final double rads = Math.toRadians(rotation);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final double w = (int) Math.floor(width_origin * cos + height_origin * sin);
        final double h = (int) Math.floor(height_origin * cos + width_origin * sin);
        final int size_line = (int) (w * h);
        int[] pixels_temp = new int[size_line];

        final BufferedImage image = new BufferedImage(width_origin, height_origin, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, image.getWidth(), image.getHeight(), pixels_origin, 0, image.getWidth());
        final BufferedImage rotatedImage = new BufferedImage((int) w, (int) h, BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads, 0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);
        System.out.println("w: " + w + " h: " + h + " cos: " + cos + " sin: " + sin);
        //pixels = pixels_temp;*/

        rotatedImage.getRGB(0, 0, rotatedImage.getWidth(), rotatedImage.getHeight(), pixels_temp, 0, rotatedImage.getWidth());
        width_origin = (int) w;
        height_origin = (int) h;
        pixels_origin = pixels_temp;
        resize(width, height);
    }
}
