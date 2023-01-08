package game.lib;


import game.project.GameProject;
import game.project.build.BuildProject;
import game.project.prefabs.Prefab;
import game.project.prefabs.PrefabFolder;

import java.io.File;

public class Main {


    public Main() {
        load();
    }

    private void load(){

        GameProject gameProject = new GameProject();
        gameProject.setDirectory(new File("C:\\Users\\carlo\\Documents\\Engine-Java\\untitled"));
        BuildProject buildProject = new BuildProject(gameProject);
        buildProject.start();
      

    }

    public static void main(String[] args) {

        new Main();
        // Image icon = new Image(f);

        System.out.println("Hello Word!");
    }
}
