package game.components.properties;

import game.components.view.objects.GameObjectProperties;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

public class ScriptProperties extends ComponentProperties<VBox> {

    private String file;
    private TextField textField;
    public ScriptProperties() {
        super( new VBox());
    }

    public static MenuItem creatingProperties(GameObjectProperties properties) {
        MenuItem item = new MenuItem("Script");
        item.setOnAction(e -> {
            ScriptProperties scriptProperties = new ScriptProperties();
            properties.addItemBack(scriptProperties.getView());
            //properties.getGameObject().addComponent(scriptProperties.getSprite());
        });
        return item;
    }

    @Override
    public void init() {
        textField = new TextField();
        textField.setOnAction(e -> {
            file = textField.getText().trim();
            System.out.printf("file: " + file);
        });
        ComponentLayouts.addItem(ComponentLayouts.addTitle("Script"), getView());
        ComponentLayouts.addItem(textField, getView());
    }
}
