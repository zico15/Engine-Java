package com.core.objects;


import com.core.base.IRender;
import com.core.render.Graphics2D;

public class Teste extends GameObject implements IRender {

    public Teste() {
        super();

    }

    @Override
    public void render(Graphics2D graphics2D) {
        System.out.println("Teste");

    }


}
