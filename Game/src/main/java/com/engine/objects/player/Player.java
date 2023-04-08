package com.engine.objects.player;

import com.assets.image.Images;
import com.engine.component.event.EventAction;
import com.engine.component.event.EventController;
import com.engine.component.navmesh.Node;
import com.engine.component.navmesh.NavMesh;
import com.engine.graphics.Graphics;
import com.engine.graphics.animation.Animation;
import com.engine.graphics.animation.Animator;
import com.engine.objects.base.GameObject;
import com.engine.objects.interfaces.IObject;
import com.engine.system.GameLoop;
import com.engine.system.events.EventKeys;
import com.engine.system.events.EventMouse;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends GameObject {





    private double speed;

    private boolean isSelect;

    public NavMesh navMesh;

    public EventAction action;




    public Player(){
        action = null;
        navMesh = new NavMesh();
        rectangle = new Rectangle(0, 0, 32, 64);
        speed = 10;
        isSelect = false;
        initAnimation(Images.load("199570-1eec6aa4de82198b4a03cee532c63972.jpg"), 5);
        EventKeys.addEventKeyPressed(e -> {
            if (e.charValue() == 'S')
                move(0, e.charValue());
            else if (e.charValue() == 'A')
                move(1, e.charValue());
            else if (e.charValue() == 'D')
                move(2, e.charValue());
            else if (e.charValue() == 'W')
                move(3, e.charValue());
        });
        EventMouse.addEventMouseClicked(e -> {
            if ( rectangle.contains(e.getX(), e.getY()))
                isSelect = true;
            else{
                int x = ((int) e.getX() ) / 32, y = ((int) e.getY() ) / 32;
                if (e.getButton().ordinal() == 1)
                    navMesh.setDistinction(rectangle.getX(), rectangle.getY(), e.getX(), e.getY(), getDirection());
            }
        });
    }

    private void move(int index, char c)
    {
        rectangle.setX(rectangle.getX() + (((c == 'D' ? speed : 0.0) - (c == 'A' ? speed : 0.0)) + GameLoop.TPF));
        rectangle.setY(rectangle.getY() + (((c == 'S' ? speed : 0.0) - (c == 'W' ? speed : 0.0)) + GameLoop.TPF));
        animation.play(index);
    }

    private void initAnimation(Image image, double speed){
        Animator up = new Animator(image, speed, false);
        up.addFrames(0, 243, 3, 54, 81, 1);
        animation.addAnimator(up, Node.TOP);
        Animator down = new Animator(image, speed, false);
        int y = 1;
        down.addFrames(0, 0, 3, 54, 81, 1);
        animation.addAnimator(down, Node.DOWN);
        Animator left = new Animator(image, speed, false);
        left.addFrames(0, 81, 3, 54, 81, 1);
        animation.addAnimator(left, Node.LEFT);
        Animator right = new Animator(image, speed, false);
        right.addFrames(0, 162, 3, 54, 81, 1);
        animation.addAnimator(right, Node.RIGHT);
        animation.play(Node.TOP);
    }
    @Override
    public void onRender(Graphics gc) {

        if (!navMesh.getPath().isEmpty())
        {
            gc.getContext().setFill(Color.PAPAYAWHIP)  ;
            for (Node l : NavMesh.path){
                gc.getContext().fillOval((l.x * 32) + 13,  (l.y * 32) +  13, 6, 6);
            }
        }
        if (isSelect){
            gc.getContext().setFill(Color.GREEN);
            gc.getContext().fillOval(rectangle.getX() - 2,  rectangle.getY()  + 10, rectangle.getWidth(), (rectangle.getHeight() / 2));
        }
        animation.draw(gc, rectangle.getX() - 2 , rectangle.getY() - rectangle.getHeight() / 2, rectangle.getWidth(), rectangle.getHeight());
    }

    int count;
    @Override
    public void onUpdate(double tpf) {
        navMesh.onUpdate(tpf, this);
        if (action == null || action.status == EventController.typeEvent.COMPLETED){
            action = EventController.getEvent();
            if (action != null)
                action.execute(this);
        }
    }


    public void TestUpdate(double tpf) {
        System.out.println("TPF: " + tpf);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }


}
