package game.lib;

import game.project.GameProject;
import game.project.build.BuildProject;
import game.project.build.classBuild.CreateClassFile;

public class GameTeste extends GameProject   {


     public  GameTeste() {
         addComponent(new Sprite("image"));
         setVector(new Vector2D(50,70, 100, 150));
     }

}