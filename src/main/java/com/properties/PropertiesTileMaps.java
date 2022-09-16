package com.properties;

import com.system.FileSistem;
import com.tree.TreeItemObject;
import engine2d.objects.TileMaps;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;

public class PropertiesTileMaps extends PropertiesBase{

    public PropertiesTileMaps(TreeItemObject item){
        super(item);
    }
    private BorderPane tileMap(){
        TileMaps tileMaps = (TileMaps) item.ob;
        BorderPane list = new BorderPane();
        alignmentAll(list);
        list.setFocusTraversable(false);
        ImageView view = new ImageView();
        ScrollPane scrollPane = new ScrollPane(view);
        alignmentAll(scrollPane);
        list.setCenter(scrollPane);
        TextField img = new TextField(tileMaps.getFile());
        img.setEditable(false);
        img.setOnMouseClicked(e -> {
            File file = FileSistem.openFile();
            if (file == null || file.isDirectory())
                return;
            tileMaps.setImage(file);
            img.setText(tileMaps.getFile());
            System.out.println(tileMaps.fileImage.getAbsolutePath());
            view.setImage(FileSistem.getImage(tileMaps.fileImage));
        });
        VBox v = new VBox();
        v.setPrefWidth(0);
        v.setMinWidth(0);
        v.getChildren().addAll(itemTitle(20), itemName(20), newItem("Image", img, 20));
        list.setTop(v);
        return list;
    }

    @Override
    public Node getProperties() {
        if (properties != null)
            return properties;
        properties = new AnchorPane();
        alignmentAll(properties);
        properties.getChildren().add(tileMap());
        return properties;
    }
}
