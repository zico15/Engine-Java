package game.project.build;

import game.components.tree.base.fileType;
import game.core.system.FileSystemGame;
import game.project.GameEngine;
import game.project.GameProject;
import game.project.build.classBuild.ClassFileGameObject;
import game.project.build.classBuild.ClassFileScene;

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

    private List<File> temp = new ArrayList<>();

    public BuildProject(GameProject project) {
        this.project = project;
        file = new File(project.getDirectory(), "Build");
        file.mkdirs();
        files.add("jar");
        files.add("cmf");
        //files.add("cvfm");
       // jar cmf Hello.mf Hello.jar Hello.class Hello.java
        files.add(".\\src\\META-INF\\MANIFEST.MF");
        files.add(".\\Build\\" +project.getName() + ".jar");
        // create class
        project.getScenes().forEach(scene -> {
            ClassFileScene classFileScene = new ClassFileScene(scene, scene.getPackageName());
            classFileScene.save(new File(project.getDirectory(), scene.getName() + ".java"));
            //ClassFileGameObject.createFiles(scene, project.getDirectory());
        });
        getFiles(project.getDirectory());
    }


    @Override
    public void run() {
        try {
            creatingClassFiles();
            creatingJarFile();
            GameEngine.resourceTreeView.load(project.getDirectory());
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }finally {
           deleteFilesTemp();
        }
    }

    private void deleteFilesTemp(){
        temp.forEach(f -> {
            if (f.exists()) {
                System.out.println(f + " " + f.delete());
            }
        });
    }
    private void getFiles(File dir)
    {
        if (dir.exists()  && !"Build".equals(dir.getName()))
        {
            for (File f : dir.listFiles()){
                String strFile = f.getPath().replace(project.getDirectory().getPath(), ".").trim();
                if (FileSystemGame.getExtensionType(f) == fileType.FILE_JAVA) {
                    filesJava.add(strFile);
                    temp.add(new File(f.getPath().replace(".java", ".class").trim()));
                    filesClass.add(strFile.replace(".java", ".class").trim());
                }
                else if (f.isDirectory())
                    getFiles(f);
                else if (FileSystemGame.getExtensionType(f) != fileType.FILE_CLASS)
                    files.add(strFile);
            }
        }
    }

    /***
     * javac files.java
     * * * ***/
    public void creatingClassFiles() throws IOException, InterruptedException {
        int index = 0;
        String[] args = new String[filesJava.size() + 1];
        args[index++] = "javac";
        for (String f : filesJava)
            args[index++] = f;
        System.out.println("CLASS -> ARGS: " + Arrays.toString(args));
        new ProcessBuilder(args).directory(project.getDirectory()).start().waitFor();
    }

    public void creatingJarFile() throws IOException, InterruptedException {
        int index = 0;
        String[] args = new String[(filesClass.size() + files.size())];
        for (String f : files)
            args[index++] = f;
        for (String f : filesClass) {
            args[index++] = f;
        }
        System.out.println("JAR -> ARGS: " + Arrays.toString(args));
        new ProcessBuilder(args).directory(project.getDirectory()).start().waitFor();
    }


}
