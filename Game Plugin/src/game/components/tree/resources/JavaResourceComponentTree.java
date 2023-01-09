package game.components.tree.resources;

import com.properties.components.Layouts;
import com.system.FileSystem;
import com.tree.TreeBase;
import com.tree.TreeViewController;
import game.project.GameEngine;
import game.project.prefabs.Prefab;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class JavaResourceComponentTree extends ResourceComponentTree{

    private Tab tab;

    private final TextArea textArea;
    public JavaResourceComponentTree(Prefab prefab, TreeViewController controller, File file) {
        super(prefab, controller, file);
        textArea = new TextArea();
        Layouts.alignment(textArea, Layouts.ALL);
    }

    @Override
    public void preview() {
        textArea.setText(FileSystem.readFile(getFile()));
        if (tab == null)
            tab = TreeBase.newTab(getFile().getName(), new AnchorPane(textArea));
        if (!GameEngine.tabPaneMain.getTabs().contains(tab))
            GameEngine.tabPaneMain.getTabs().add(tab);
        GameEngine.tabPaneMain.getSelectionModel().select(tab);
    }
}
