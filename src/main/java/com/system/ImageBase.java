package com.system;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;

public class ImageBase {

    private static final HashMap<String, Image> ICONS = new HashMap<>();

    public static Image getIcons(String fileName){
        String name  = fileName.toUpperCase();
        if (ICONS.containsKey(name))
                return ICONS.get(name);
        Image img = new Image(fileName, 15, 15, false, false);
        ICONS.put(name, img);
        return img;
    }
}
