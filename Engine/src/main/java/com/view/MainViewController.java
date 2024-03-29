package com.view;

import com.system.FileSystem;
import com.system.SystemLib;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import plugins.Plugins;

import java.io.File;

public class MainViewController extends ComponentView {

    public static Stage stage;
    @FXML
    protected void installPlugins() {
        File file = FileSystem.openFile(new FileChooser.ExtensionFilter("JAVA files (*.jar)", "*.jar"));
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