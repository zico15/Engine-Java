package com.properties.components;


import com.list.TextFieldNumber;
import com.properties.PropertiesBase;
import com.properties.PropertiesItem;
import com.system.FileSistem;
import engine2d.components.Sprite;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;

import static com.properties.PropertiesItem.*;

/***
 * ImageProperties creates a property item to be inserted in the property list
 * @author edos-san
 * @version 0.1
 * **/
public class ImageProperties extends ComponentProperties {


    private Sprite sprite;
    private TextField img;
    private TextFieldNumber w,h;

    public ImageProperties(PropertiesBase properties) {
        super(properties);
        sprite = null;
    }

    public ImageProperties(Sprite sprite, PropertiesBase properties) {
        super(properties);
        this.sprite = sprite;
        System.out.println("sw: " + sprite.getWidth() + " sh: "+ sprite.getHeight());
        if (sprite != null && sprite.getFile() != null)
            load(sprite.getFile());

    }

    @Override
    public void createProperties() {
        VBox v = new VBox();
        alignmentAll(v);
        img = new TextField();
        img.setEditable(false);
        img.setOnMouseClicked(e -> {
            File file = FileSistem.openFile();
            if (file == null || file.isDirectory())
                return;
            load(file);
        });
        v.getChildren().add(itemTitle("Image", 20));
        v.getChildren().add(newItem("URL: ", img, 20));
        w = new TextFieldNumber(item.ob.vector.getWidth(), Pos.CENTER);
        w.setOnAction(e -> {
            if (sprite != null && w.getText().trim().length() > 0)
                sprite.setWidth(Integer.parseInt(w.getText().trim()));
        });
        h = new TextFieldNumber(item.ob.vector.getHeight(), Pos.CENTER);
        h.setOnAction(e -> {
            if (sprite != null && h.getText().trim().length() > 0)
                sprite.setHeight(Integer.parseInt(h.getText().trim()));
        });
        v.getChildren().add(newItem("W:", w , 20));
        v.getChildren().add(newItem("H: ", h , 20));
       getChildren().add(v);
    }

    private void load(File file){
        if (sprite == null) {
            sprite = new Sprite(file);
            component = sprite;
            item.ob.addComponent(sprite);
            System.out.println("add image");
        }
        else
            sprite.load(file);
        w.setText(String.valueOf(sprite.getWidth()));
        h.setText(String.valueOf(sprite.getHeight()));
        img.setText(file.getName());
    }


}
