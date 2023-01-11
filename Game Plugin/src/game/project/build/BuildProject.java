package game.project.build;

import com.system.FileSystem;
import game.components.tree.base.fileType;
import game.core.objects.Scene;
import game.core.system.FileSystemGame;
import game.project.GameEngine;
import game.project.GameProject;
import game.project.build.classBuild.ClassFileScene;
import javafx.scene.control.TextInputDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BuildProject extends Thread {


    private final GameProject project;
    private final File fileBuild;

    private File fileScenes;

    private File fileGameOpenGL;

    private String pathEnv = null;
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

    public boolean get_env_java_home(){

       String path =  System.getenv().get("JAVA_HOME");

       if (path == null || path.isEmpty()) {
           TextInputDialog dialog = new TextInputDialog(null);
           dialog.setTitle("JAVA_HOME");
           dialog.setHeaderText("Path");

           Optional<String> result = dialog.showAndWait();
           if (result.isPresent()) {

               pathEnv = result.get() + " /bin/";
           }
           else
               pathEnv = null;
       }
       else
           pathEnv = path + "/bin/";
       return  pathEnv != null && !pathEnv.isEmpty();

    }


    @Override
    public void run() {
        if (get_env_java_home()){
        try {
            FileSystem.copy(getClass().getResource("/resources/gameopengl/GameOpenGL.jar"), fileGameOpenGL);
            String[] classScene = new String[project.getScenes().size()];
            // create class
            for (int j = 0; j < project.getScenes().size(); j++) {
                Scene scene = project.getScenes().get(j);
                ClassFileScene classFileScene = new ClassFileScene(scene, scene.getPackageName());
                classFileScene.save(new File(fileScenes, scene.getName() + ".java"));
                classScene[j++] = ("game/project/scenes/" + scene.getName() + ".java");
            }
            compileFileToJar(project.getName()+".jar", classScene);
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
     * javac -cp .\GameOpenGL.jar \game\project\scenes\Scene_1.java
     * jar uf .\GameOpenGL.jar .\game\project\scenes\Scene_1.class
     * **/
    public void insertFileToJar(String jarName, String filesName[]) throws IOException, InterruptedException {
        String args[] = new String[filesName.length + 3];
        args[0] = pathEnv + "jar";
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
        args[0] = pathEnv + "javac";
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
