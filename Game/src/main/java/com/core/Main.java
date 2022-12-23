package com.core;

import com.core.base.ImageBuffer;
import com.core.components.Sprite;
import com.core.objects.GameObject;
import com.core.objects.Scene;
import com.core.objects.Teste;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;

public class Main extends JFrame {

    public static int count = 0;

    public Main() {
        super("Tester");
        load();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void load() {


        Scene scene = new Scene();
        Teste t = (Teste) scene.addGameObject(new Teste());
       /* while (count++ < 15)
        {
            try {
                scene.objectsTemp.forEach(c -> {
                    c.setParent(scene);
                    scene.getChildren().add(c);
                    BaseEvents.addEvents(scene.baseEvents, c);
                });
                System.out.println("===================");
                scene.render(null);
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
*/
        System.out.println(scene);
        add(new CanvasGame());
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class CanvasGame extends JPanel {

        private final Graphics2DGame graphics2DGame = new Graphics2DGame();
        private final Scene scene = new Scene();
        private GameObject gameObject = new GameObject();
        private Sprite sprite;

        {
            try {
                sprite = new Sprite(new File(getClass().getResource("/img/nave.jpg").toURI()));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        public CanvasGame() {

            /*gameObject.vector.setX(100);
            gameObject.vector.setY(100);*/
            /*gameObject.addComponent(sprite);
            ImageBuffer buffer =  sprite.getBuffer();
            buffer.setScale(0.2f);
            buffer.setRotate(25);*/
            //buffer.resize(64, 64);
           /* ImageBuffer buffer = new ImageBuffer(32,32);
            for (int y = 0; y < 32; y++) {
                for (int x = 0; x < 32; x++)
                    buffer.setArgb(0 + x, 0 + y, 255,64, 224, 208);
            }
            sprite.setBuffer(buffer);*/
            scene.addGameObject(gameObject);
            GameObject o = new GameObject();
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED);
            scene.render(graphics2DGame);
            g2d.drawImage(graphics2DGame.buffer, 0, 0, null);
            // g2d.drawImage(sprite.buffer.image, 0,0, null);
        }

        private class Graphics2DGame extends com.core.render.Graphics2D {
            public BufferedImage buffer = new BufferedImage(5000, 5000, BufferedImage.TYPE_INT_ARGB);


            public void drawImage(ImageBuffer image, int x1, int y1) {
                for (int y = 0; y < image.getHeight(); y++) {
                    for (int x = 0; x < image.getWidth(); x++)
                        buffer.setRGB(x + x1, y + y1, image.getArgb(x, y));
                }
            }
        }

    }
}