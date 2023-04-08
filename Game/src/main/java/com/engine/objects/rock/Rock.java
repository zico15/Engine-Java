package com.engine.objects.rock;

import com.assets.image.Images;
import com.engine.component.event.EventAction;
import com.engine.component.event.EventController;
import com.engine.component.event.EventObject;
import com.engine.component.navmesh.Node;
import com.engine.graphics.Graphics;
import com.engine.objects.base.GameObject;
import com.engine.objects.map.Map;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;

public class Rock extends GameObject {

    private Image image;


    int count = 0;

    private Consumer<EventObject> event = null;

    public Rock(int x, int y){
        image = Images.load("4694c01608d227c560304858e5bedfa2.png");
        rectangle.setX(x);
        rectangle.setY(y);
        Map.map[x / 32][y / 32] = 'B';
        EventAction event = new EventAction(this, (int) rectangle.getX(), (int) rectangle.getY());
        event.add(e -> {
            if ( !e.player.navMesh.setDistinction(e.player.rectangle.getX(), e.player.rectangle.getY(), rectangle.getX() - 32 , rectangle.getY(),  c -> e.next()))
                if ( !e.player.navMesh.setDistinction(e.player.rectangle.getX(), e.player.rectangle.getY(), rectangle.getX() + 32 , rectangle.getY(),  c -> e.next()))
                    if ( !e.player.navMesh.setDistinction(e.player.rectangle.getX(), e.player.rectangle.getY(), rectangle.getX() , rectangle.getY()  + 32,  c -> e.next()))
                        if ( !e.player.navMesh.setDistinction(e.player.rectangle.getX(), e.player.rectangle.getY(), rectangle.getX() , rectangle.getY()  - 32,  c -> e.next())) {
                            e.completed();
                            System.out.println("No path");
                        }

        });
        event.add(e -> {
            GameObject.setLook(e.player, this);
            e.next();
        });

        event.add(e -> {
            Consumer<EventObject> fin = ((o) -> {
                System.out.print("Time: " + count + " / 200\r");
                if (count++ > 200) {
                    e.completed();
                    this.event = null;
                    onDestroy();
                }
            });
            this.event = fin;
        });
        EventController.addE(event);
    }



    @Override
    public void onRender(Graphics gc) {
        gc.drawImage(image, 480, 0, 32, 32, rectangle.getX(), rectangle.getY(), 32, 32);
    }

    @Override
    public void onUpdate(double tpf) {
        if (event != null)
            event.accept(null);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
