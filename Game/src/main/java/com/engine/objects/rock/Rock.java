package com.engine.objects.rock;

import com.assets.image.Images;
import com.engine.component.event.EventAction;
import com.engine.component.event.EventController;
import com.engine.graphics.Graphics;
import com.engine.objects.interfaces.IObject;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Rock implements IObject {

    private Image image;

    public final Rectangle rectangle = new Rectangle();
    public Rock(){
        image = Images.load("4694c01608d227c560304858e5bedfa2.png");
        rectangle.setY(480);
        rectangle.setX(480);
        EventAction event = new EventAction(this, (int) rectangle.getX(), (int) rectangle.getY());
        event.add(e -> {
            if ( e.player.navMesh.setDistinction(e.player.rectangle.getX(), e.player.rectangle.getY(), rectangle.getX() - 32, rectangle.getY(),  c -> e.next()))
                ;
        });
        event.add(e -> System.out.println("Final"));
        EventController.addE(event);
    }
    @Override
    public void onRender(Graphics gc) {
        gc.drawImage(image, 480, 0, 32, 32, rectangle.getX(), rectangle.getY(), 32, 32);
    }

    @Override
    public void onUpdate(double tpf) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
