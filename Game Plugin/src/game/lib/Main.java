package game.lib;


import game.core.components.Sprite;
import game.core.objects.GameObject;
import game.core.objects.Scene;
import game.core.transforme.Vector2D;
import game.project.build.classBuild.ClassFileGameObject;
import game.project.build.classBuild.CreateClassFile;

import java.io.File;
import java.net.URISyntaxException;

public class Main {


    public Main() {
        load();
    }

    private void load(){

        File file = null;
        try {
            file = new File(getClass().getResource("/resources/gameopengl/GameOpenGL.jar").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println(file + " / " + file.exists());
        /*GameObject gameObject = new GameObject("GameTeste");
        gameObject.setVector(new Vector2D(50, 70, 100, 150));
        gameObject.addComponent(new Sprite("image"));

        ClassFileGameObject classFile = new ClassFileGameObject(gameObject, "game.lib");
        classFile.addImport("game.project.GameProject");
        classFile.addImport("game.project.build.BuildProject");
        classFile.addImport("game.project.build.classBuild.CreateClassFile");
        classFile.setExtendsName("GameProject");
        classFile.save(new File(System.getProperty("user.dir"), "asset/GameObject_1.java"));*/
        /*GameProject gameProject = new GameProject();
        gameProject.setDirectory(new File("C:\\Users\\carlo\\Documents\\Engine-Java\\untitled"));
        BuildProject buildProject = new BuildProject(gameProject);
        buildProject.start();*/
      

    }

    public static void main(String[] args) {

        new Main();
        // Image icon = new Image(f);

        System.out.println("Hello Word!");
    }
}
