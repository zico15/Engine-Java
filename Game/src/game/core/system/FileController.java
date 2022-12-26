package game.core.system;

import java.io.*;

public class FileController {

    public static final void save(File file, Object ob) {
        try {
            if (!file.exists())
                file.createNewFile();
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(file));
            objOutput.writeObject(ob);
            objOutput.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Object read(File file) {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                return reader.readObject();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
