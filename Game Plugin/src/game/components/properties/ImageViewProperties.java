package game.components.properties;

import com.properties.components.Layouts;
import com.system.FileSystem;
import game.components.view.objects.GameObjectProperties;
import game.core.components.Sprite;
import game.project.GameEngine;
import javafx.geometry.Insets;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.File;

public class ImageViewProperties extends ScrollPane {

    private final GameObjectProperties properties;

    private final Sprite sprite;
    private ImageView imageView;



    public ImageViewProperties(GameObjectProperties properties, Sprite sprite) {
        this.properties = properties;
        this.sprite = sprite;
        imageView = new ImageView();
        if (sprite != null) {
            System.out.println("ImageViewProperties: true image: " + sprite.getImage() != null);
            imageView.setImage(sprite.getImage());
        }
        getChildren().add(imageView);
    }

    public static MenuItem creatingProperties(GameObjectProperties properties) {
        MenuItem item = new MenuItem("Image View");
        item.setOnAction(e -> {

        });
        return item;
    }

    private void addItem(Region node) {
        Layouts.alignment(node, Layouts.LEFT_RIGHT);
        getChildren().add(node);
      //  setMargin(node, new Insets(0, 0, 8, 0));
    }

    public Sprite getSprite() {
        return sprite;
    }

}
