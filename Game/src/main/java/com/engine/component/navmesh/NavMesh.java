package com.engine.component.navmesh;

import com.engine.graphics.Graphics;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class NavMesh {

    private Link mesh[][];

    private Image img;

    private int dx, dy;

    private final ArrayList<Link> open = new ArrayList<>();

    private final ArrayList<Link> close = new ArrayList<>();

    public static final ArrayList<Link> path = new ArrayList<>();

    public NavMesh(){
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

    public void setDistinction(char map[][], int x, int y, int dx, int dy)
    {
        open.clear();
        close.clear();
        path.clear();
        if (isEmpty(map, dx, dy))
        {
            this.dx = dx;
            this.dy = dy;
            createNodes(map, new Link(x, y, 0, null), dx, dy);
            open.clear();
            close.clear();
        }
    }

    private void createNodes(char map[][], Link link, int dx, int dy)
    {
        if (link != null) {
            close.add(link);
            open.remove(link);
            if (link.x == dx && link.y == dy)
            {
                System.out.println("PATH OK");

                while (link != null)
                {
                      path.add(0, link);
                      link = link.preview;
                }
                System.out.println(path);
            }
            else {
                //LEFT
                addNode(link, map, link.x - 1, link.y, dx, dy);
                //RIGHT
                addNode(link, map, link.x + 1, link.y, dx, dy);
                //TOP
                addNode(link, map, link.x, link.y - 1, dx, dy);
                //DOWN
                addNode(link, map, link.x, link.y + 1, dx, dy);
                //LEFT TOP
                addNode(link, map, link.x - 1, link.y - 1, dx, dy);
                //RIGHT TOP
                addNode(link, map, link.x + 1, link.y - 1, dx, dy);
                //LEFT DOWN
                addNode(link, map, link.x - 1, link.y + 1, dx, dy);
                //RIGHT DOWN
                addNode(link, map, link.x + 1, link.y + 1, dx, dy);
                Link lowerCost = null;
                for (Link l : open) {
                    if (lowerCost == null || lowerCost.g > l.g)
                        lowerCost = l;
                }
                createNodes(map, lowerCost, dx, dy);
            }
        }
    }

    private void addNode(Link preview, char map[][], int x, int y, int dx, int dy){
        if (isPossible(map, x, y)) {
            if (!(x != preview.x && y != preview.y) || (isEmpty(map,  preview.x, y) && isEmpty(map,  x, preview.y)))
                open.add(new Link(x, y, getG(x, y, dx, dy), preview));
        }
    }

    private int getG( int x, int y, int dx, int dy)
    {
        int c = ((x != dx) && (y != dy)) ? 10 : 10;
        x = (x - dx);
        x = x < 0 ? -x : x;
        y = (y - dy);
        y = y < 0 ? -y : y;
        return (x + y) * c;
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
       // Graphics.gc.drawImage(img, 0, 0);
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

    private boolean isEmpty(char map[][], int x, int y)
    {
        return ((y >= 0 && y < map.length) && (x >= 0 && x < map[0].length) && map[x][y] == 'A');
    }

    private boolean isPossible(char map[][], int x, int y)
    {
        if  (isEmpty(map, x, y)){
            for (Link e : close) {
                if (x == e.x && y == e.y)
                    return false;
            }
            return true;
        }
        return false;
    }
    private void createLink(Link link, char map[][], int x, int y, Link.Dir dir)
    {
        if (link != null && (x >= 0 && x < map.length) && (y >= 0 && y < map.length) && map[y][x] == 'A') {
            link.setLink((map[y][x] == 'A'), dir);
        }
    }

    public boolean isEqual(int x, int y)
    {
          for (Link l : path)
          {
              if (l.x == x && l.y == y)
                  return true;
          }
          return false;
    }
    public void draw(Graphics gc)
    {
        if (img != null)
            gc.drawImage(img, 0, 0);
    }
}
