package com.system;

import com.Main;
import com.MainViewController;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public static void createFileJava(File file)
    {
        File project = MainViewController.PROJECT.getDirectory();
        String pack = project.toString().replace(project.getName(), "");
        pack = file.toString().replace(pack, "");
        pack = pack.toString().replace(file.getName(), "");
        pack = pack.replaceAll("/", ".").trim();
        if (pack.lastIndexOf(".") == (pack.length() - 1))
            pack = pack.substring(0, pack.lastIndexOf("."));
        String name = file.getName();
        if (name.contains("."))
            name  = name.substring(0, name.lastIndexOf("."));
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (BufferedWriter bw = new BufferedWriter(new PrintWriter(file))) {
                    bw.write("package " + pack + ";");
                    bw.newLine();
                    bw.newLine();
                    bw.write("public class " + name + " {");
                    bw.newLine();
                    bw.newLine();
                    bw.write("}");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static final String readFile(File file)
    {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return  data;
    }

    public static Image getImage(File file){
        Image img = null;
        try {
            img = new Image(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return null;
        }
        return img;
    }
}
