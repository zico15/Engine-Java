package game.project.build;

import game.components.tree.base.fileType;
import game.core.system.FileSystemGame;
import game.project.GameProject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildProject extends Thread {


    private final GameProject project;
    private final File file;

    private List<String> filesJava = new ArrayList<>();

    private List<String> filesClass = new ArrayList<>();
    private List<String> files = new ArrayList<>();

    public BuildProject(GameProject project) {
        this.project = project;
        file = new File(project.getDirectory(), "Build");
        file.mkdirs();
        getFiles(project.getDirectory());
    }
    @Override
    public void run() {
        try {
            creatingClassFiles();
            try {
                creatingJarFile(project.getName(), "as");
            } catch (IOException e) {
                System.err.println("JAR: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("CLAS: " + e.getMessage());
        }
    }

    private void getFiles(File dir)
    {
        if (dir.exists()  && !"Build".equals(dir.getName()))
        {

            for (File f : dir.listFiles()){
                String strFile = f.getPath().replace(project.getDirectory().getPath(), ".").trim();
                if (FileSystemGame.getExtensionType(f) == fileType.FILE_JAVA) {
                    filesJava.add(strFile);
                    filesClass.add(strFile.replace(".java", ".class").trim());
                }
                else if (!"Build".equals(dir.getName()))
                    files.add(strFile);
                if (f.isDirectory())
                    getFiles(f);
            }
        }
    }

    /***
     * javac files.java
     * ***/
    public void creatingClassFiles() throws IOException {
        int index = 0;
        String[] args = new String[filesJava.size() + 1];
        args[index++] = "javac";
        for (String f : filesJava)
            args[index++] = f;
        System.out.println("CLASS -> ARGS: " + Arrays.toString(args));
       new ProcessBuilder(args).directory(project.getDirectory()).start();
    }

    public void creatingJarFile(String projectName, String classMainName) throws IOException {
        int index = 0;
        String[] args = new String[(filesClass.size() + files.size() + 1)];
        args[index++] = "jar cvfm " + projectName + ".jar";
        for (String f : files)
            args[index++] = f;
        for (String f : filesClass)
            args[index++] = f;
        System.out.println("JAR -> ARGS: " + Arrays.toString(args));
        Process por = new ProcessBuilder(args).directory(project.getDirectory()).start();
       // new ProcessBuilder("java -jar", (projectName + ".jar")).start();
    }


}
