package com.assets.image;

import java.util.HashMap;
import javafx.scene.image.Image;
public class Images {
    private final static HashMap<String, Image> images = new HashMap<>();
    public static Image load(String fileName)
    {
        if (images.containsKey(fileName))
            return images.get(fileName);
        Image img = new Image(Images.class.getResourceAsStream(fileName));
        images.put(fileName, img);
        return  img;
    }
}
