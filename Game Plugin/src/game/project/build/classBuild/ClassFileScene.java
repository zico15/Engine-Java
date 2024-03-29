package game.project.build.classBuild;

import game.core.components.ComponentBase;
import game.core.components.Script;
import game.core.components.Sprite;
import game.core.objects.GameObject;
import game.core.objects.Scene;
import game.core.objects.TileMaps;

public class ClassFileScene extends CreateClassFile {

    private final Scene scene;
    private int index = 0;

    private String space;
    public ClassFileScene(Scene scene, String packageName) {
        super(scene.getName(), "game.project.scenes");
        addImport("game.core.components.*");
        addImport("game.core.transforme.*");
        addImport("game.core.objects.*");
        addImport("java.util.List");
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
        block.setThisName(thisName);
        block.add("     %s.setVector(new Vector2D(%s));", thisName, gameObject.getVector());
        for (ComponentBase component : gameObject.getComponents()){
                component.addComponentToScript(this, block);
        }
    }

    private  void loadGameObject(functionBlock block, GameObject gameObject, String thisName, String type){
        String obName = "ob_" + (++index);
        block.setThisName(thisName);
        block.add("{");
        block.add("     %s %s = new %s(\"%s\");", type, obName, type,  gameObject.getName());
        addComponents(block, gameObject, obName);
        if (gameObject instanceof TileMaps)
            loadTileMaps(block, (TileMaps) gameObject, obName);
        gameObject.getChildren().forEach(o -> loadGameObject(block, o, obName, o.getClass().getSimpleName()));
        block.add("     %s.addGameObject(%s);", thisName, obName);
        block.add("}");
    }

    private void loadTileMaps(functionBlock block, TileMaps  tileMaps,  String thisName){
        String tiles = "";
        int size = tileMaps.getTiles().size();
        for (int i = 0; i < size; i++)
            tiles += String.format(("new TileMaps.Tile(%s)" + (i + 1 < size ? ", " : "")), tileMaps.getTiles().get(0).toString());
        block.add("     %s.getTiles().addAll(List.of(%s));", thisName, tiles);
    }


}
