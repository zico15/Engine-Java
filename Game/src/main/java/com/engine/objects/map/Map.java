package com.engine.objects.map;

import com.assets.image.Images;
import com.engine.component.navmesh.NavMesh;
import com.engine.graphics.Graphics;
import com.engine.objects.interfaces.IObject;
import com.engine.objects.player.Player;
import com.engine.system.events.EventKeys;
import com.engine.system.events.EventMouse;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Map implements IObject {

    private Image image;

    public static  Image IMG;

    private  final int size = 32;
    private char map[][];

    private NavMesh navMesh;

    private boolean isTest;


    public Map(){
        Image rock = Images.load("4694c01608d227c560304858e5bedfa2.png");
        map = new char [64][64];

        Canvas canvas = new Canvas(size * 64, size * 64);
        for (int y = 0; y < 64; y++)
        {
            for (int x = 0; x < 64; x++){
                 map[x][y] = 'A';
                 canvas.getGraphicsContext2D().
                 drawImage(rock, 192, 32, 32, 32, x * size, y * size, size, size);
            }
        }
        map[1][2] = 'B';
        map[2][2] = 'B';
        map[3][2] = 'B';
        map[4][2] = 'B';
        image = canvas.snapshot(null, null);
        Map.IMG = image;
        initTest();
    }
    private void initTest(){
        navMesh = new NavMesh();
        navMesh.generateLinks(map, image);
        EventKeys.addEventKeyPressed(e -> {
            if (e.charValue() == 'P')
                System.out.println("P");
            else if (e.charValue() == 'O')
                System.out.println("O");
            else if (e.charValue() == 'I')
                System.out.println("I");
            else if (e.charValue() == 'M')
                isTest = !isTest;
        });
        EventMouse.addEventMouseClicked(e -> {
            int x = ((int) e.getX() ) / 32, y = ((int) e.getY() ) / 32;
            //System.out.println("Mouse: " + x  + " / " + y);
            if (e.getButton().ordinal() == 3)
                map[x][y] = map[x][y] == 'A' ? 'B' : 'A';
            else  if (e.getButton().ordinal() == 1)
                navMesh.setDistinction(map, (int) Player.p.getX() / 32, (int) Player.p.getY() / 32, x, y);
            navMesh.generateLinks(map, image);
        });
    }
    @Override
    public void onRender(Graphics gc) {

        if (isTest)
            navMesh.draw(gc);
        else
            gc.drawImage(image, 0, 0);
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
