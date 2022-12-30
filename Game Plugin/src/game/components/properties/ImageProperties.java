package game.components.properties;

import com.properties.components.Layouts;
import com.system.FileSystem;
import game.components.view.objects.GameObjectProperties;
import game.core.components.Sprite;
import game.project.GameEngine;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.File;

public class ImageProperties extends VBox {

    private final GameObjectProperties properties;

    private Sprite sprite = new Sprite();
    private final TextField textFieldFile;

    public ImageProperties(GameObjectProperties properties) {
        this.properties = properties;
        Label lt = new Label("Image");
        lt.setTextAlignment(TextAlignment.CENTER);
        lt.setMinHeight(25);
        Pane pane = new Pane(lt);
        pane.setStyle("-fx-background-color: #0093ff;");
        textFieldFile = new TextField();
        textFieldFile.addEventHandler(MouseEvent.MOUSE_PRESSED, evt -> {
            File file = FileSystem.openFile(GameEngine.gameProject.getDirectory());
            if (file != null && file.exists() && file.isFile() && sprite.load(file))
                textFieldFile.setText(file.getName());
            else
                textFieldFile.setText(sprite.getFile() != null ? sprite.getFile().getName() : "");
        });
        addItem(pane);
        addItem(textFieldFile);
    }

    public ImageProperties(GameObjectProperties properties, Sprite sprite) {
        this(properties);
        this.sprite = sprite;
        if (sprite.getFile() != null)
            textFieldFile.setText(sprite.getFile().getName());
        else
            textFieldFile.setText(null);
    }

    public static MenuItem creatingProperties(GameObjectProperties properties) {
        MenuItem item = new MenuItem("Image");
        item.setOnAction(e -> {
            ImageProperties imageProperties = new ImageProperties(properties);
            properties.addItemBack(imageProperties);
            properties.getGameObject().addComponent(imageProperties.getSprite());
        });
        return item;
    }

    private void addItem(Region node) {
        Layouts.alignment(node, Layouts.LEFT_RIGHT);
        getChildren().add(node);
        setMargin(node, new Insets(0, 0, 8, 0));
    }

    public Sprite getSprite() {
        return sprite;
    }

}
