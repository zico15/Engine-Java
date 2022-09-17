package com.properties.components;


import com.properties.PropertiesBase;
import com.system.FileSistem;
import engine2d.components.Sprite;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.io.File;

import static com.properties.PropertiesBase.newItem;

public class ImagePropertides extends CompomentProperties{


    private Sprite sprite;
    File fileImage;
    public ImagePropertides(PropertiesBase properties) {
        super(properties);
    }


    @Override
    public Node getProperties() {
        TextField img = new TextField();
        img.setEditable(false);
        img.setOnMouseClicked(e -> {
            File file = FileSistem.openFile();
            if (file == null || file.isDirectory())
                return;
            fileImage = file;
            if (component != null)
                return;
            img.setText(file.getName());
            sprite = new Sprite();
            sprite.load(file);
            component = sprite;
            item.ob.addComponent(sprite);
        });
        return (newItem("Image", img, 20));
    }



}
