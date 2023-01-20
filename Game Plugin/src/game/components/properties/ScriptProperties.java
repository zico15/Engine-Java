package game.components.properties;

import com.properties.components.Layouts;
import com.view.DialogPane;
import game.components.tree.base.fileType;
import game.components.view.objects.GameObjectProperties;
import game.components.view.resources.ResourceTreeView;
import game.components.view.resources.ResourceTreeViewSelect;
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

    private static ResourceTreeViewSelect resourceTreeView = new ResourceTreeViewSelect();
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
        textField.setFocusTraversable(false);
        textField.setOnMousePressed(e -> {
            DialogPane  dialogPane = new DialogPane("Scripts");
            resourceTreeView.load(GameEngine.gameProject.getDirectory(), s  -> {
                script.setFile(s.getFile());
                textField.setText(s.getFile().getName());
                System.out.printf("dialogPane: " + s.getFile());
                dialogPane.close();
            } ,fileType.FILE_JAVA);
            AnchorPane pane = new AnchorPane(resourceTreeView);
            Layouts.alignment(resourceTreeView, Layouts.ALL);
            dialogPane.setPane(pane);
            dialogPane.showAndWait(null);
        });
        ComponentLayouts.addItem(ComponentLayouts.addTitle("Script"), getView());
        ComponentLayouts.addItem(textField, getView());
    }
}
