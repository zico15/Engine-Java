package com.engine.graphics.animation;

import com.engine.graphics.Graphics;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Animation {

    private ArrayList<Animator> animators = new ArrayList<>();
    private boolean isPlay;
    private Animator animator;

    public void addAnimator(Animator animator){
        this.animators.add(animator);
    }

    public void play(int index)
    {
        if (index >= 0 && index < animators.size()) {
            if (animator != animators.get(index)) {
                animator = animators.get(index);
                animator.setActive(true);
            }
        }
        else
            animator = null;
        this.isPlay = animator != null;
        if (isPlay)
            animator.play();
    }

    public void stop()
    {
        if (isPlay)
            animator.setActive(false);
        this.isPlay = false;

    }

    public void draw(Graphics gc, double x, double y, double dw, double dh)
    {
        if (isPlay)
            animator.draw(gc, x, y, dw, dh);
    }
}
