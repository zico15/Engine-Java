package game.project.build.classBuild;

import game.core.components.ComponentBase;
import game.core.components.Sprite;
import game.core.objects.GameObject;
import game.core.objects.Scene;
import game.core.objects.TileMaps;

import java.io.File;

public class ClassFileScene extends CreateClassFile {

    private final Scene scene;
    private int index = 0;

    private String space;
    public ClassFileScene(Scene scene, String packageName) {
        super(scene.getName(), packageName);
        addImport("game.core.components.*");
        addImport("game.core.transforme.*");
        setExtendsName("Scene");
        this.scene = scene;
        functionBlock constructor = createBock("public", null, scene.getName(), null);
        constructor.add("setVector(new Vector2D(" + scene.getVector() + "));");
        constructor.add("init_game_objects();");
        functionBlock init = createBock("private", "void", "init_game_objects", null);
        scene.getChildren().forEach(gameObject -> {
            space = "";
            loadGameObject(init, gameObject, "this", gameObject.getClass().getSimpleName());
            index = 0;
        });
    }


    private void addComponents(functionBlock block, GameObject gameObject, String thisName){
        block.add("     %s.setVector(new Vector2D(%s));", thisName, scene.getVector());
        for (ComponentBase component : gameObject.getComponents()){
            if (component instanceof Sprite)
                block.add("     %s.addComponent(new Sprite(\""+ ((Sprite)component).getFile().getPath()+"\"));", thisName);
        }
    }

    private  void loadGameObject(functionBlock block, GameObject gameObject, String thisName, String type){
        String obName = "ob_" + (++index);
        block.add("{");
        block.add("     %s %s = new %s(\"%s\");", type, obName, type,  gameObject.getName());
        addComponents(block, gameObject, obName);
        gameObject.getChildren().forEach(o -> loadGameObject(block, o, obName, o.getClass().getSimpleName()));
        block.add("     %s.addGameObject(%s);", thisName, obName);
        block.add("}");
    }


}
