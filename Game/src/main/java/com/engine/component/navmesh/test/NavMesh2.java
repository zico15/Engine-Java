package com.engine.component.navmesh.test;

import com.engine.graphics.Graphics;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class NavMesh2 {

    private Link mesh[][];

    private Image img;

    private int dx, dy;

    private ArrayList<Link> open = new ArrayList<>();

    private ArrayList<Link> close = new ArrayList<>();

    private ArrayList<Link> path = new ArrayList<>();

    public NavMesh2(){
        mesh = null;
        img = null;
        dx = -1;
        dy = -1;
    }

    public void setDistinction(int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    public void setDistinction(int x, int y, int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    public void generateLinks(char map[][], Image image){
        img = null;
        this.mesh = new Link[map.length][map[0].length];
        Canvas canvas = new Canvas(map.length * 32, map[0].length * 32);
        canvas.getGraphicsContext2D().setFill(Color.TRANSPARENT);
        canvas.getGraphicsContext2D().fillRect(0, 0 , map.length * 32, map[0].length * 32);
        canvas.getGraphicsContext2D().drawImage(image, 0, 0);
        for (int x = 0; x < mesh.length; x++)
        {
            for (int y = 0; y < mesh[0].length; y++)
            {
                mesh[x][y] = new Link(x, y, 0, null);
                createLink(mesh[x][y], map,  x, y - 1, Link.Dir.TOP);
                createLink(mesh[x][y], map,  x, y + 1, Link.Dir.DOWN);
                createLink(mesh[x][y], map,  x + 1, y, Link.Dir.RIGHT);
                createLink(mesh[x][y], map,  x - 1, y, Link.Dir.LEFT);
                if (map[x][y] == 'A')
                    printLink(mesh[x][y], canvas.getGraphicsContext2D(), x * 32, y * 32);
                else
                {
                    canvas.getGraphicsContext2D().setFill(Color.BLACK);
                    canvas.getGraphicsContext2D().fillRect(x * 32, y * 32, 32, 32);
                }
            }
        }
        img = canvas.snapshot(null, null);
    }

    private void printLink(Link link, GraphicsContext gc, double x, double y){

        double cx = x + 16, cy = y + 16;
        gc.setStroke(Color.RED);
        gc.strokeRect(x, y, 32, 32 );
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(1);
        if (link.isTop())
            gc.strokeLine(cx, cy, cx, y);
        if (link.isDown())
            gc.strokeLine(cx, cy, cx, y +  32);
        if (link.isLeft())
            gc.strokeLine(cx, cy, x, cy);
        if (link.isRight())
            gc.strokeLine(cx, cy, x + 32, cy);
        cx -= 3;
        cy -= 3;
        gc.setFill((dx == ((int)x / 32) && dy == ((int)y / 32)) ? Color.BLUE : Color.GREEN);
        gc.fillOval(cx,  cy, 6, 6);
    }

    private void createLink(Link link, char map[][], int x, int y, Link.Dir dir)
    {
        if (link != null && (x >= 0 && x < map.length) && (y >= 0 && y < map.length)) {
            link.setLink((map[x][y] == 'A'), dir);
            if ((map[x][y] != 'A'))
                System.out.println("TESTE");
        }
    }

    public void draw(Graphics gc)
    {
        if (img != null)
            gc.drawImage(img, 0, 0);
    }
}
