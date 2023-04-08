package com.engine.system;

import com.engine.objects.scene.Scene;
import com.engine.graphics.Graphics;
import com.engine.system.events.EventKeys;
import javafx.application.Platform;

public class GameLoop {

    private boolean running; 
    private long lastTime; 
    private final double TARGET_FPS = 60;
    private final double TARGET_TIME_BETWEEN_UPDATES = 1000000000 / TARGET_FPS; 
    private int fpsCounter;
    private long fpsTimer;

    private Scene scene;
    private final Graphics graphics;

    public static double TPF;

    private static GameLoop gameLoop;

    public GameLoop(Scene scene, Graphics graphics) {
        this.scene = scene;
        this.graphics = graphics;
        this.gameLoop = this;
        EventKeys.addEventKeyPressed(e -> {
            if (e.charValue() == '\u001B')
                GameLoop.exit();
        });
    }


    public void start() throws InterruptedException {
        running = true;
        long now;
        while (running) { 
            now = System.nanoTime();
            TPF = (now - lastTime) / 1000000000.0;
            lastTime = now; 

            if (scene != null){
                update(TPF);
                render();
                //graphics.drawImage(Graphics.gc.getCanvas().snapshot(null, null), 0, 0);


            }
            // Count FPS and print every second 
            fpsCounter++;
            if (System.nanoTime() - fpsTimer >= 1000000000) {
                //System.out.println("FPS: " + fpsCounter);
                fpsCounter = 0; 
                fpsTimer = System.nanoTime();
            }
            Thread.sleep((long) ((TARGET_TIME_BETWEEN_UPDATES - TPF) / 1000000));
        } 
    }

    public static void exit(){
        if (gameLoop != null)
            gameLoop.stop();
        Platform.exit();
        System.exit(0);
    }
 
    public void stop() { 
        running = false; 
    }

    private void update(double tpf) { 
        scene.onUpdate(tpf);
    } 
 
    public void render() {
        graphics.render();
        scene.onRender(graphics);


    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}


