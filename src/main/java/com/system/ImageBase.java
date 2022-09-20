package com.system;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.IntBuffer;
import java.util.HashMap;

public class ImageBase {

    public static final String ICON_SCENE = "/com/icons/scene.png";
    public static final String ICON_OBJECT = "/com/icons/object.png";
    public static final String ICON_FOLDER = "/com/icons/folder.png";
    public static final String ICON_JAVA = "/com/icons/java.png";

    public static final String ICON_FILE = "/com/icons/file.png";

    public static final String ICON_TILEMAP = "/com/icons/tilemap.png";

    private static final HashMap<String, Image> ICONS = new HashMap<>();

    public static Image getIcons(String fileName) {
        String name = fileName.toUpperCase();
        if (ICONS.containsKey(name))
            return ICONS.get(name);
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            URL url = ImageBase.class.getResource(fileName);
            if (url == null)
                return null;
            fileName = url.toExternalForm();
        }
        Image img = new Image(fileName, 15, 15, false, false);
        ICONS.put(name, img);
        return img;
    }

    public static BufferedImage convert(Image fxImage) {
        int width = (int) Math.ceil(fxImage.getWidth());
        int height = (int) Math.ceil(fxImage.getHeight());

        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        int[] buffer = new int[width];
        PixelReader reader = fxImage.getPixelReader();
        WritablePixelFormat<IntBuffer> format =
                PixelFormat.getIntArgbInstance();
        for (int y = 0; y < height; y++) {
            reader.getPixels(0, y, width, 1, format, buffer, 0, width);
            image.getRaster().setDataElements(0, y, width, 1, buffer);
        }
        return image;
    }
}
