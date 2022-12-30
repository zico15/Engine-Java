package game.lib;


import game.project.GameProject;
import game.project.prefabs.Prefab;
import game.project.prefabs.PrefabFolder;

import java.io.File;

public class Main {


    public Main() {
        load();
    }

    private void load(){

        GameProject gameProject = new GameProject();
        Prefab<File> p = new PrefabFolder(new File("/nfs/homes/edos-san/Documents/casa/.pref"));
        gameProject.addPrefab(p);
        //File file = p.getObject();
        // FileSystemGame.writePrefab(new File("/nfs/homes/edos-san/Documents/teste1"), p);
        //  p.setName("casa");

    }

    public static void main(String[] args) {

        new Main();
        // Image icon = new Image(f);

        System.out.println("Hello Word!");
    }
}
