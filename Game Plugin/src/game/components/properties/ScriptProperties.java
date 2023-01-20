package game.components.properties;

import com.properties.components.Layouts;
import com.view.DialogPane;
import game.components.view.objects.GameObjectProperties;
import game.components.view.resources.ResourceTreeView;
import game.core.components.Script;
import game.project.GameEngine;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

import java.io.File;

public class ScriptProperties extends ComponentProperties<VBox> {

    private String file;

    private Script script = new Script();

    private static ResourceTreeView resourceTreeView = new ResourceTreeView();
    private TextField textField;
    public ScriptProperties() {
        super( new VBox());
    }

    public static MenuItem creatingProperties(GameObjectProperties properties) {
        MenuItem item = new MenuItem("Script");
        item.setOnAction(e -> {
            ScriptProperties scriptProperties = new ScriptProperties();
            properties.addItemBack(scriptProperties.getView());
            properties.getGameObject().addComponent(scriptProperties.script);
        });
        return item;
    }

    @Override
    public void init() {
        textField = new TextField();
        textField.setOnAction(e -> {
            DialogPane  dialogPane = new DialogPane("Scripts");
            resourceTreeView.load(GameEngine.gameProject.getDirectory());
            AnchorPane pane = new AnchorPane(resourceTreeView);
            Layouts.alignment(resourceTreeView, Layouts.ALL);
            dialogPane.setPane(pane);
            dialogPane.showAndWait(null);
            /*file = textField.getText().trim();
            script.setFile(new File(GameEngine.gameProject.getDirectory(), file));
            System.out.printf("file: " + file);*/
        });
        ComponentLayouts.addItem(ComponentLayouts.addTitle("Script"), getView());
        ComponentLayouts.addItem(textField, getView());
    }
}
