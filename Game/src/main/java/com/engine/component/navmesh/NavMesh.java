package com.engine.component.navmesh;


import com.engine.objects.interfaces.IObject;
import com.engine.objects.map.Map;
import com.engine.objects.player.Player;

import java.util.ArrayList;
import java.util.function.Consumer;

public class NavMesh {

    private final ArrayList<Node> open = new ArrayList<>();

    private final ArrayList<Node> close = new ArrayList<>();

    public static final ArrayList<Node> path = new ArrayList<>();

    private Consumer<IObject> onCompleted;

    public NavMesh(){
        this.onCompleted = null;
    }


    /**
     * the event onCompleted is executed only once, when the object reaches the destination
     */
    public boolean setDistinction(double x, double y, double dx, double dy, Consumer<IObject> onCompleted){
        if (setDistinction(x, y, dx, dy, 0)) {
            this.onCompleted = onCompleted;
            return true;
        }
        return false;
    }

    public boolean setDistinction(double x, double y, double dx, double dy, int direction)
    {
        open.clear();
        close.clear();
        path.clear();
        x /= Map.SIZE;
        y /= Map.SIZE;
        dx /= Map.SIZE;
        dy /= Map.SIZE;
        if (isEmpty(Map.map, (int) dx, (int) dy))
        {
            createNodes(Map.map, new Node(x , y , 0, null, direction), (int) dx, (int) dy);
            open.clear();
            close.clear();
        }
        return  !path.isEmpty();
    }

    private void createPath(Node node){
        while (node != null && node.preview != null)
        {
            path.add(0, node);
            node = node.preview;
        }
    }
    private void createNodes(char map[][], Node node, int dx, int dy)
    {
        if (node != null) {
            close.add(node);
            open.remove(node);
            if (node.x == dx && node.y == dy)
                createPath(node);
            else {
                //LEFT
                addNode(node, map, -1, 0, dx, dy, Node.LEFT);
                //RIGHT
                addNode(node, map, 1, 0, dx, dy, Node.RIGHT);
                //TOP
                addNode(node, map, 0, -1, dx, dy, Node.TOP);
                //DOWN
                addNode(node, map, 0, 1, dx, dy, Node.DOWN);

                //LEFT TOP
                addNode(node, map, -1, -1, dx, dy, Node.LEFT_TOP);
                //RIGHT TOP
                addNode(node, map, 1, -1, dx, dy, Node.RIGHT_TOP);
                //LEFT DOWN
                addNode(node, map, -1, 1, dx, dy, Node.LEFT_DOWN);
                //RIGHT DOWN
                addNode(node, map, 1, 1, dx, dy, Node.RIGHT_DOWN);

                Node lowerCost = null;
                for (Node l : open) {
                    if (lowerCost == null || lowerCost.g > l.g)
                        lowerCost = l;
                }
                createNodes(map, lowerCost, dx, dy);
            }
        }
    }

    private void addNode(Node preview, char map[][], int x, int y, int dx, int dy, int direction){
        x += preview.x;
        y += preview.y;
        if (isPossible(map, x, y)) {
            if (!(x != preview.x && y != preview.y) || (isEmpty(map,  (int) preview.x, y) && isEmpty(map,  x, (int) preview.y)))
                open.add(new Node(x, y, getG(x, y, dx, dy), preview, direction));
        }
    }

    private int getG( int x, int y, int dx, int dy)
    {
        int cx = x != dx ? 14 : 10;
        int cy = y != dy ? 14 : 10;
        x = (x - dx);
        x = x < 0 ? -x : x;
        y = (y - dy);
        y = y < 0 ? -y : y;
        return ((cx * x) + (cy * y));
    }

    private boolean isEmpty(char map[][], int x, int y)
    {
        return ((y >= 0 && y < map.length) && (x >= 0 && x < map[0].length) && map[x][y] == 'A');
    }

    private boolean isPossible(char map[][], int x, int y)
    {
        if  (isEmpty(map, x, y)){
            for (Node e : close) {
                if (x == e.x && y == e.y)
                    return false;
            }
            return true;
        }
        return false;
    }

    public final ArrayList<Node> getPath(){
        return path;
    }

    private int count;

    public void onUpdate(double tpf, Player player) {
        if (path.size() > 0 && count++ > 10)
        {
            Node link = path.get(0);
            path.remove(link);
            player.rectangle.setX(link.x * 32);
            player.rectangle.setY(link.y * 32);
            int dir = link.getDirection();
            player.animation.play(dir > 3 ? 0 : dir);
            count = 0;
            if (onCompleted != null && path.isEmpty()) {
                onCompleted.accept(player);
                onCompleted = null;
            }
        }
    }

}
