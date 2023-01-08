package game.core.objects;


import game.core.base.IRender;
import game.opengl.renderer.Graphics2D;

public class Teste extends GameObject implements IRender {

    public Teste() {
        super();

    }

    @Override
    public void render(Graphics2D graphics2D) {
        System.out.println("Teste");

    }


}
