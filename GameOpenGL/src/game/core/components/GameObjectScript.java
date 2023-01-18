package game.core.components;

import game.core.objects.GameObject;

public class GameObjectScript extends ComponentBase{

    private GameObject gameObject;


    public final GameObject getGameObject() {
        return gameObject;
    }


    @Override
    public void load_system() {
        super.load_system();
        gameObject = (GameObject) getParent();
    }

}
