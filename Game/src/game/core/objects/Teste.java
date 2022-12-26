package game.core.objects;


import game.core.base.IRender;
import javafx.scene.canvas.GraphicsContext;

public class Teste extends GameObject implements IRender {

    public Teste() {
        super();

    }

    @Override
    public void render(GraphicsContext graphics2D) {
        System.out.println("Teste");

    }


}
