package com.engine.objects.base;

import com.engine.component.navmesh.Node;
import com.engine.graphics.Graphics;
import com.engine.graphics.animation.Animation;
import com.engine.objects.interfaces.IObject;
import com.engine.objects.scene.Scene;
import javafx.scene.shape.Rectangle;

public abstract class GameObject implements IObject {


    public final Animation animation = new Animation();
    public Rectangle rectangle = new Rectangle();
    protected int direction;


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.animation.play(direction);
        this.direction = direction;
    }

    public static void setLook(GameObject in, GameObject to){
        int x = (int) in.rectangle.getX() - (int) to.rectangle.getX();
        int y = (int) in.rectangle.getY() - (int) to.rectangle.getY();
        if (x != 0)
            in.setDirection(x > 0 ? Node.LEFT : Node.RIGHT);
        else
            in.setDirection(y > 0 ? Node.TOP : Node.DOWN);
    }

    @Override
    public void onDestroy() {
        Scene.SCENE.remove(this);
    }
}
