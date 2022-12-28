package game.core.system;

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
}
