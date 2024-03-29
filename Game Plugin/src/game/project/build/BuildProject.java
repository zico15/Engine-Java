package game.project.build;

import com.system.FileSystem;
import game.components.tree.base.fileType;
import game.core.objects.Scene;
import game.core.system.FileSystemGame;
import game.project.GameEngine;
import game.project.GameProject;
import game.project.build.classBuild.ClassFileScene;
import javafx.scene.control.TextInputDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BuildProject extends Thread {


    private final GameProject project;
    private final File fileBuild;

    private File fileScenes;

    private File fileGameOpenGL;


    private static String pathEnv = null;
    /*{
        try {
            fileGameOpenGL = new File(getClass().getResource("/resources/gameopengl/GameOpenGL.jar").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }*/


    private List<String> filesJava = new ArrayList<>();

    private List<String> filesClass = new ArrayList<>();
    private List<String> files = new ArrayList<>();

    private List<File> temp = new ArrayList<>();

    public BuildProject(GameProject project) {

        System.out.println(getClass().getResource("resources/gameopengl/GameOpenGL.jar"));
        this.project = project;
        fileBuild = new File(project.getDirectory(), "Build");
        fileBuild.mkdirs();
        fileScenes = new File(fileBuild, "game/project/scenes");
        fileScenes.mkdirs();
        /*files.add("jar");
        files.add("cmf");
        //files.add("cvfm");
       // jar cmf Hello.mf Hello.jar Hello.class Hello.java
        files.add(".\\src\\META-INF\\MANIFEST.MF");
        files.add(".\\Build\\" +project.getName() + ".jar");*/
        fileGameOpenGL = new File(fileBuild, project.getName()+".jar");
        System.out.println(getClass().getResource("/resources/gameopengl/GameOpenGL.jar"));
        System.out.println( System.getProperty("user.dir"));

        //getFiles(project.getDirectory());
    }

    /****
     * linux: update-alternatives --config java
     * ****/
    public static String getPathEnv() {

        if (pathEnv == null || pathEnv.isEmpty()) {
            String path = System.getenv().get("JAVA_HOME");
            if (path == null || path.isEmpty()) {
                TextInputDialog dialog = new TextInputDialog(null);
                dialog.setTitle("JAVA_HOME");
                dialog.setHeaderText("Path (Ex: java-11-openjdk-amd64/bin/)");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {

                    pathEnv = result.get().trim();
                } else
                    pathEnv = null;
            } else
                pathEnv = (path.trim() + "/bin/").trim();
        }
        return pathEnv;
    }

    public boolean get_env_java_home(){
      return  getPathEnv() != null && !getPathEnv().isEmpty();
    }


    @Override
    public void run() {
        if (get_env_java_home()){
        try {
            filesJava.clear();
            FileSystem.copy(getClass().getResource("/resources/gameopengl/GameOpenGL.jar"), fileGameOpenGL);
            copyJava(project.getDirectory());
            String[] classJava = new String[project.getScenes().size() + filesJava.size()];
            // create class
            int j;
            for (j = 0; j < project.getScenes().size(); j++) {
                Scene scene = project.getScenes().get(j);
                ClassFileScene classFileScene = new ClassFileScene(scene, scene.getPackageName());
                classFileScene.save(new File(fileScenes, scene.getName() + ".java"));
                classJava[j] = ("game/project/scenes/" + scene.getName() + ".java");
                System.out.println("java: " + "game/project/scenes/" + scene.getName() + ".java");
            }
            for (String java : filesJava) {
                System.out.println("java: " + java);
                classJava[j++] = java;
            }
            compileFileToJar(fileGameOpenGL.getName(), classJava);
            fileScenes.delete();
            GameEngine.resourceTreeView.load(project.getDirectory());
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }finally {
           deleteFilesTemp();
        }}
    }

    private void deleteFilesTemp(){
        temp.forEach(f -> {
            if (f.exists()) {
                System.out.println(f + " " + f.delete());
            }
        });
    }

    public static void exeJar(GameProject project) throws IOException, InterruptedException {
           File file = new File(project.getDirectory(), "Build");
            if (file.exists()){
                    String args[] = new String[3];
                    args[0] = getPathEnv() + "java";
                    args[1] = "-jar";
                    args[2] = (project.getName().trim()+".jar");
                    Process p =  new ProcessBuilder(args).directory(file).start();
                    BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String ligne = "";
                    while ((ligne = output.readLine()) != null) {
                        System.out.println(ligne);
                    }
            }
            else
                System.err.println("Not Build!");
    }


    void copyJava(File file)
    {
        if (file.exists()  && file.isDirectory() && !"Build".equals(file.getName())) {
            for (File f : file.listFiles()) {
                if (FileSystemGame.getExtensionType(f) == fileType.FILE_JAVA) {
                    try {
                        String java = "game/project" + FileSystemGame.getSubFile(f);
                        filesJava.add(java);
                        File dest = new File(fileBuild, java);
                        System.out.println("dest: " + dest);
                        FileSystem.copy(f, dest);
                    } catch (IOException e) {
                        //throw new RuntimeException(e);
                    }
                } else if (f.isDirectory()) {
                    copyJava(f);
                }
            }
        }
    }

    /***
     * javac -cp .\GameOpenGL.jar \game\project\scenes\Scene_1.java
     * jar uf .\GameOpenGL.jar .\game\project\scenes\Scene_1.class
     * **/
    public void insertFileToJar(String jarName, String filesName[]) throws IOException, InterruptedException {
        String args[] = new String[filesName.length + 3];
        args[0] = getPathEnv() + "jar";
        args[1] = "uf";
        args[2] = jarName;
        for (int i = 0; i < filesName.length; i++)
            args[3 + i] = filesName[i];
        new ProcessBuilder(args).directory(fileBuild).start().waitFor();
    }

    /***
     * javac -cp .\GameOpenGL.jar \game\project\scenes\Scene_1.java
     * jar uf .\GameOpenGL.jar .\game\project\scenes\Scene_1.class
     * **/
    public void compileFileToJar(String jarName, String filesName[]) throws IOException, InterruptedException {
        String args[] = new String[filesName.length + 3];
        args[0] = getPathEnv() + "javac";
        args[1] = "-cp";
        args[2] = jarName;
        for (int i = 0; i < filesName.length; i++)
            args[3 + i] = filesName[i];
        new ProcessBuilder(args).directory(fileBuild).start().waitFor();
        for (int i = 0; i < filesName.length; i++)
            filesName[i] = filesName[i].replaceAll(".java", ".class");
        insertFileToJar(jarName, filesName);
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
