package com.properties.components;


import com.properties.PropertiesBase;
import com.system.FileSistem;
import engine2d.components.Sprite;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.File;

import static com.properties.PropertiesBase.*;

/***
 * ImageProperties creates a property item to be inserted in the property list
 * @author edos-san
 * @version 0.1
 * **/
public class ImageProperties extends ComponentProperties {


    private Sprite sprite;
    File fileImage;
    public ImageProperties(PropertiesBase properties) {
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
            if (sprite == null) {
                sprite = new Sprite();
                component = sprite;
            }
            fileImage = file;
            sprite.load(file);
            img.setText(file.getName());
            item.ob.addComponent(sprite);
        });
        return (newItem("Image", img, 20));
    }



}
