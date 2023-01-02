package game.core.system;

import game.components.tree.base.fileType;
import game.project.prefabs.Prefab;

import java.io.*;

public class FileSystemGame {
    public static final Object readGameObject(File file) {
        try {
            ObjectInputStream reader = new ObjectInputStream( new FileInputStream(file));
            return reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static final void writeGameObject(File file, Object ob) {
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

    public static final void writePrefab(File file, Prefab prefab)
    {
        if (!file.exists())
            file.mkdirs();
        writeGameObject(new File(file, ".pref"), prefab);
    }

    public static final Prefab readPrefab(File file) {
        try {
            ObjectInputStream reader = new ObjectInputStream( new FileInputStream(file));
            Object object = reader.readObject();
            Prefab prefab = null;
            if (object !=  null && object instanceof Prefab)
            {
                prefab = (Prefab) object;
                prefab.load();
            }
            return prefab;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static fileType getExtensionType(File file) {
        if (file.isDirectory()) {
            return "Build".equals(file.getName()) ? fileType.FOLDER_BUILD : fileType.FOLDER_ANY;
        }
        String name = file.getName();
        String extension = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1,name.length()) : name;
        extension = extension.trim().toLowerCase();
        switch (extension)
        {
            case "java": return fileType.FILE_JAVA;
            case "cpp": return fileType.FILE_CODE;
            case "scene": return fileType.FILE_SCENE;
            case "png": return fileType.FILE_IMAGE;
        }
        return  fileType.FILE_ANY;
    }
}
