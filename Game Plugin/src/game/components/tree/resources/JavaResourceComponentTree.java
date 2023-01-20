package game.components.tree.resources;

import com.properties.components.Layouts;
import com.system.FileSystem;
import com.tree.TreeBase;
import com.tree.TreeViewController;
import game.core.system.FileSystemGame;
import game.project.GameEngine;
import game.project.prefabs.Prefab;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class JavaResourceComponentTree extends ResourceComponentTree{

    private Tab tab;

    private final TextArea textArea;
    private final  Button button;
    public JavaResourceComponentTree(Prefab prefab, TreeViewController controller, File file) {
        super(prefab, controller, file);
        textArea = new TextArea();
        Layouts.alignment(textArea, Layouts.ALL);
        Layouts.alignment(textArea, Layouts.TOP, 30);
        button = new Button("Save");
        button.setStyle("-fx-base: #ee2211;");
        button.setOnAction(e -> FileSystem.createFileTxt(getFile(), textArea.getText().trim()));
        Layouts.alignment(button, Layouts.TOP, 5);
        Layouts.alignment(button, Layouts.LEFT, 100);
    }

    @Override
    public void preview() {
        textArea.setText(FileSystem.readFile(getFile()));
        if (tab == null)
            tab = TreeBase.newTab(getFile().getName(), new AnchorPane(button, textArea));
        if (!GameEngine.tabPaneMain.getTabs().contains(tab))
            GameEngine.tabPaneMain.getTabs().add(tab);
        GameEngine.tabPaneMain.getSelectionModel().select(tab);
    }
}
