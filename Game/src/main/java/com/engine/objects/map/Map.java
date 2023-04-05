package com.engine.objects.map;

import com.assets.image.Images;
import com.engine.component.navmesh.test.NavMesh;
import com.engine.graphics.Graphics;
import com.engine.objects.interfaces.IObject;
import com.engine.objects.player.Player;
import com.engine.system.events.EventKeys;
import com.engine.system.events.EventMouse;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Map implements IObject {

    private Image image;

    public static  Image IMG;

    public  static final int SIZE = 32;
    public static char map[][];

    private boolean isTest;

    private ArrayList<int[]> test = new ArrayList<>();


    public Map(){
        Image rock = Images.load("4694c01608d227c560304858e5bedfa2.png");
        map = new char [64][64];

        Canvas canvas = new Canvas(SIZE * 64, SIZE * 64);
        for (int y = 0; y < 64; y++)
        {
            for (int x = 0; x < 64; x++){
                 map[x][y] = 'A';
                 canvas.getGraphicsContext2D().
                 drawImage(rock, 192, 32, 32, 32, x * SIZE, y * SIZE, SIZE, SIZE);
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
            if (e.getButton().ordinal() == 3) {
                map[x][y] =  'B';
                test.add(new int[]{x, y});
            }

        });
    }
    @Override
    public void onRender(Graphics gc) {
        gc.drawImage(image, 0, 0);
        gc.getContext().setFill(Color.BLACK);
        for (int a[] : test)
        {
            gc.getContext().fillRect(a[0] * 32, a[1] * 32, 32, 32);
        }
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
