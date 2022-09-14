package com.system;

import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class ImageBase {

    public static final String ICON_SCENE = "/com/icons/scene.png";
    public static final String ICON_OBJECT = "/com/icons/object.png";
    public static final String ICON_FOLDER = "/com/icons/folder.png";
    public static final String ICON_JAVA = "/com/icons/java.png";

    public static final String ICON_FILE = "/com/icons/file.png";
    private static final HashMap<String, Image> ICONS = new HashMap<>();

    public static Image getIcons(String fileName){
        String name  = fileName.toUpperCase();
        if (ICONS.containsKey(name))
            return ICONS.get(name);
        File file = new File(fileName);
        if (!file.exists() || !file.isFile())
        {
            URL url = ImageBase.class.getResource(fileName);
            if (url == null)
                return null;
            fileName = url.toExternalForm();
        }
        Image img = new Image(fileName, 15, 15, false, false);
        ICONS.put(name, img);
        return img;
    }
}
