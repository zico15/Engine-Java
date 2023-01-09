package game.project.build.classBuild;

import game.core.components.ComponentBase;
import game.core.components.Sprite;
import game.core.objects.GameObject;
import game.core.transforme.Vector2D;

import java.io.File;

public class ClassFileGameObject extends CreateClassFile {

    private final GameObject gameObject;
    public ClassFileGameObject(GameObject gameObject, String packageName) {
        super(gameObject.getName(), packageName);
        this.gameObject = gameObject;
        functionBlock constructor = createBock("public", null, gameObject.getName(), null);
        addComponents(constructor);
        constructor.add("setVector(new Vector2D(" + gameObject.getVector() + "));");
        addGameObjects(constructor);
    }

    private void addComponents(functionBlock block){
        for (ComponentBase component : gameObject.getComponents()){
            if (component instanceof Sprite)
                block.add("addComponent(new Sprite(\""+ ((Sprite)component).getFile().getPath()+"\"));");
        }
    }

    private void addGameObjects(functionBlock block){
        for (GameObject ob : gameObject.getChildren())
                block.add("addGameObject(new "+ ob.getName()+ "());");
    }

    public static void createFiles(GameObject gameObject, File file){
        new ClassFileGameObject(gameObject, gameObject.getPackage()).save(new File(file, gameObject.getName() + ".java"));
        gameObject.getChildren().forEach(ob -> createFiles(ob, file));
    }

}
