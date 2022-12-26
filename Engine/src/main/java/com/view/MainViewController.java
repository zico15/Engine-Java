package com.view;

import com.canva.CanvasView;
import com.system.FileSistem;
import com.system.SystemLib;
import com.tree.TreeResourceController;
import com.tree.TreeSceneController;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import plugins.Plugins;

import java.io.File;

public class MainViewController extends ComponentView {


    @FXML
    protected void installPlugins() {
        File file = FileSistem.openFile(new FileChooser.ExtensionFilter("JAVA files (*.jar)", "*.jar"));
        if (file == null)
            return;
        System.out.println(file);
        Plugins p = SystemLib.installPlugins(file);
        try {
            p.install();
        } catch (NullPointerException e)
        {
            System.err.println("installPlugins: " + e.getMessage());
        }
    }

    @FXML
    protected void uninstallPlugins() {

    }

    @FXML
    protected void onHelloButtonClick() {

    }

    @Override
    void initializeView() {

    }
}