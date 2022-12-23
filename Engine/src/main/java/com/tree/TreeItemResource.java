package com.tree;

import com.view.MainViewController;
import com.system.FileSistem;
import com.system.ImageBase;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.File;

public class TreeItemResource extends TreeItemBase {

    public File file;
    private TextArea textArea;

    public TreeItemResource(File file) {
        setFile(file);
    }

    @Override
    public void preview() {
        System.out.println("FILE: " + file);
        if ("java".equals(type))
            previewCode();
        else
            return;
    }

    private void previewCode() {

        textArea = new TextArea();
        textArea.setText(FileSistem.readFile(file));

    }

    @Override
    public boolean delete() {
        if (file.exists())
            file.delete();
        return (true);
    }

    public void setFile(File file) {
        this.file = file;
        type = file.getName();

        if (type.contains(".")) {
            type = type.substring(type.lastIndexOf(".") + 1, type.length());
            System.out.println("type: " + type);
        } else if (file.exists() && file.isDirectory()) {
            if ("Build".equals(file.getName()))
                type = "folderBuild";
            else
                type = "folder";
        } else
            type = "file";
        super.setValue(file.getName());
        setGraphic(new ImageView(ImageBase.getIcons(getExtension(type))));
    }

}