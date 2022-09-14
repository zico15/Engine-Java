package com.system;

import com.Main;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class FileSistem {

    public static File openFolder()
    {
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Open Folder");
        return (fileChooser.showDialog(Main.stage));
    }

    public static File openFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        return (fileChooser.showOpenDialog(Main.stage));
    }

    public static File saveFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("save File");
        return (fileChooser.showSaveDialog(Main.stage));
    }

}
