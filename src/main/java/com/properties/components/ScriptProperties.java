package com.properties.components;

import com.project.Project;
import com.properties.PropertiesBase;
import com.system.FileSistem;
import engine2d.components.Script;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;

import static com.properties.PropertiesItem.*;

public class ScriptProperties extends  ComponentProperties{

    private Script script;
    private TextField textFile;

    public ScriptProperties(PropertiesBase properties) {
        super(properties);
        this.script = new Script();
    }

    public ScriptProperties(Script script, PropertiesBase properties) {
        this(properties);
        this.script = script;
        if (script != null)
            textFile.setText(script.getFile() == null ? "" :script.getFile().getName());
    }
    @Override
    public void createProperties() {
        VBox v = new VBox();
        alignmentAll(v);
        textFile = new TextField();
        textFile.setEditable(false);
        textFile.setOnMouseClicked(e -> {
            File file = FileSistem.openFile(Project.getProject().getDirectory());
            if (file == null || file.isDirectory())
                return;
            if (script != null){
                script.setFile(file);
                textFile.setText(script.getFile() == null ? "" :script.getFile().getName());
            }

        });
        v.getChildren().add(itemTitle("Script", 20));
        v.getChildren().add(newItem("File: ", textFile, 20));
        getChildren().add(v);
    }
}
