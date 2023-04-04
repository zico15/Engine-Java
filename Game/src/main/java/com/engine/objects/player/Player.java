package com.engine.objects.player;

import com.assets.image.Images;
import com.engine.component.navmesh.Link;
import com.engine.component.navmesh.NavMesh;
import com.engine.graphics.Graphics;
import com.engine.graphics.animation.Animation;
import com.engine.graphics.animation.Animator;
import com.engine.objects.interfaces.IObject;
import com.engine.system.GameLoop;
import com.engine.system.events.EventKeys;
import com.engine.system.events.EventMouse;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player implements IObject {


    private final Animation animation;

    private Rectangle rectangle;

    private double speed;

    private boolean isSelect;


    /**
     * Teste
     * */
    public static Rectangle p;

    public Player(){
        rectangle = new Rectangle(0, 0, 32, 64);
        // teste
        p = rectangle;
        speed = 10;
        isSelect = false;
        animation = new Animation();
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
            isSelect = rectangle.contains(e.getX(), e.getY());
        });
    }

    private void move(int index, char c)
    {
        rectangle.setX(rectangle.getX() + (((c == 'D' ? speed : 0.0) - (c == 'A' ? speed : 0.0)) + GameLoop.TPF));
        rectangle.setY(rectangle.getY() + (((c == 'S' ? speed : 0.0) - (c == 'W' ? speed : 0.0)) + GameLoop.TPF));
        animation.play(index);
    }

    private void initAnimation(Image image, double speed){
        Animator down = new Animator(image, speed, false);
        int y = 1;
        down.addFrames(0, 0, 3, 54, 81, 1);
        animation.addAnimator(down);
        Animator left = new Animator(image, speed, false);
        left.addFrames(0, 81, 3, 54, 81, 1);
        animation.addAnimator(left);
        Animator right = new Animator(image, speed, false);
        right.addFrames(0, 162, 3, 54, 81, 1);
        animation.addAnimator(right);
        Animator up = new Animator(image, speed, false);
        up.addFrames(0, 243, 3, 54, 81, 1);
        animation.addAnimator(up);
        animation.play(0);
    }
    @Override
    public void onRender(Graphics gc) {

        if (!NavMesh.path.isEmpty())
        {
            gc.getContext().setFill(Color.PAPAYAWHIP)  ;
            for (Link l : NavMesh.path){
                gc.getContext().fillOval((l.x * 32) + 13,  (l.y * 32) +  13, 6, 6);
            }
        }
        if (isSelect && false){
            gc.getContext().setFill(Color.GREEN);
            gc.getContext().fillOval(rectangle.getX(),  rectangle.getY() + (rectangle.getHeight() / 2) + 10, rectangle.getWidth(), (rectangle.getHeight() / 2));
        }
        gc.getContext().setFill(Color.GREEN);
        gc.getContext().fillOval(rectangle.getX()  ,  rectangle.getY() , 32, 32);
        //animation.draw(gc, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    int count;
    @Override
    public void onUpdate(double tpf) {
        if (NavMesh.path.size() > 0 && count++ > 20)
        {
            Link link = NavMesh.path.get(0);
            NavMesh.path.remove(link);
            rectangle.setX(link.x * 32);
            rectangle.setY(link.y * 32);
            count = 0;
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
