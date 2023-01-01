package com.system;

import com.Main;
import com.project.Project;
import com.view.MainViewController;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystem {

    public static File openFolder() {
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Open Folder");
        return (fileChooser.showDialog(MainViewController.stage));
    }

    public static File openFile(FileChooser.ExtensionFilter filter) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().setAll(filter);
        fileChooser.setTitle("Open File");
        return (fileChooser.showOpenDialog(MainViewController.stage));
    }

    public static File openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        return (fileChooser.showOpenDialog(MainViewController.stage));
    }

    public static File openFile(File dir) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(dir);
        fileChooser.setTitle("Open File");
        return (fileChooser.showOpenDialog(MainViewController.stage));
    }

    public static final void saveObject(File file, Object ob) {
        try {
            if (!file.exists())
                file.createNewFile();
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(file));
            objOutput.writeObject(ob);
            objOutput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Object readObject(File file) {
        Object object = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            System.out.println("load: "  +file + " inputStream: "+file.exists());
            ObjectInputStream reader = new ObjectInputStream(inputStream);
            object = reader.readObject();
            inputStream.close();
            reader.close();
            System.out.println("object: " + object != null);
            return object;
        } catch (IOException | ClassNotFoundException e) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        }
    }

    public static File saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("save File");
        return (fileChooser.showSaveDialog(MainViewController.stage));
    }

    public static void createFileJava(File file) {
        File project = Project.getProject().getDirectory();
        String pack = project.toString().replace(project.getName(), "");
        pack = file.toString().replace(pack, "");
        pack = pack.toString().replace(file.getName(), "");
        pack = pack.replaceAll("/", ".").trim();
        if (pack.lastIndexOf(".") == (pack.length() - 1))
            pack = pack.substring(0, pack.lastIndexOf("."));
        String name = file.getName();
        if (name.contains("."))
            name = name.substring(0, name.lastIndexOf("."));
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

    public static final String readFile(File file) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public static Image getImage(File file) {
        Image img = null;
        try {
            img = new Image(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return null;
        }
        return img;
    }

    public static void rename(File file, String name){
        String newFilePath = file.getAbsolutePath().replace(file.getName(), "") + name;
        File newFile = new File(newFilePath);
        if(file.renameTo(newFile)){
            System.out.println("File renamed");
        }else{
            System.out.println("Sorry! the file can't be renamed");
        }
    }

    public static void copy(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }
}
