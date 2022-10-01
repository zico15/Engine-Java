package com.properties.components;


import com.list.TextFieldNumber;
import com.properties.PropertiesBase;
import com.system.FileSistem;
import engine2d.components.Sprite;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;

import static com.properties.PropertiesBase.*;

/***
 * ImageProperties creates a property item to be inserted in the property list
 * @author edos-san
 * @version 0.1
 * **/
public class ImageProperties extends ComponentProperties {


    private Sprite sprite = null;

    public ImageProperties(PropertiesBase properties) {
        super(properties);
    }


    @Override
    public Node getProperties() {
        VBox v = new VBox();
        alignmentAll(v);
        TextField img = new TextField();
        img.setEditable(false);
        img.setOnMouseClicked(e -> {
            File file = FileSistem.openFile();
            if (file == null || file.isDirectory())
                return;
            if (sprite == null) {
                sprite = new Sprite(file);
                component = sprite;
                item.ob.addComponent(sprite);
                System.out.println("add image");
            }
            else
                sprite.load(file);
            img.setText(file.getName());

        });
        v.getChildren().add(itemTitle("Image", 20));
        v.getChildren().add(newItem("URL: ", img, 20));
        TextFieldNumber w = new TextFieldNumber(item.ob.vector.getWidth(), Pos.CENTER);
        w.setOnAction(e -> {
            if (sprite != null && w.getText().trim().length() > 0)
                sprite.setWidth(Integer.parseInt(w.getText().trim()));
        });
        TextFieldNumber h = new TextFieldNumber(item.ob.vector.getHeight(), Pos.CENTER);
        h.setOnAction(e -> {
            if (sprite != null && h.getText().trim().length() > 0)
                sprite.setHeight(Integer.parseInt(h.getText().trim()));
        });
        v.getChildren().add(newItem("W:", w , 20));
        v.getChildren().add(newItem("H: ", h , 20));
        return (v);
    }



}
