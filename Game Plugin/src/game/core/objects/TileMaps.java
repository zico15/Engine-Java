package game.core.objects;


import game.core.components.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


public class TileMaps extends GameObject {

    private Sprite sprite;

    private static int id = 1;

    private int grid = 32;

    private ArrayList<Tile> tiles = new ArrayList<>();

    public TileMaps() {
        this("tileMaps_" + id++);
    }

    public TileMaps(String name) {
        super(name);
        sprite = new Sprite();
        addComponent(sprite);
    }

    @Override
    void render(GraphicsContext graphics2D) {

        if (sprite.getImage() != null)
            getTiles().forEach(t -> drawTile(t, graphics2D));
        getChildren().forEach(c -> c.render(graphics2D));
    }

    private void drawTile(Tile tile, GraphicsContext graphics2D){
        if ((tile.x_sub + grid) <= sprite.getWidth() && (tile.y_sub + grid) <= sprite.getHeight())
            graphics2D.drawImage(sprite.getImage(), tile.x_sub, tile.y_sub, grid, grid, tile.x, tile.y, grid, grid);
    }

    public boolean setTile(int x, int y, int x_sub, int y_sub)
    {
        for (Tile t : getTiles()){
            if (t.isEqual(x, y))
            {
                t.x_sub = x_sub;
                t.y_sub = y_sub;
                return true;
            }
        }
        return getTiles().add( new Tile(x, y, x_sub , y_sub));
    }

    public void removeTile(int x, int y){
        int index = 0;
        for (Tile t : getTiles()){
            if (t.isEqual(x, y))
            {
                getTiles().remove(index);
                break;
            }
            index++;
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(String file) {
        sprite = new Sprite(file);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }


    public static class Tile implements Serializable {

        public int x;
        public int y;
        public int x_sub;
        public int y_sub;



        public Tile(int x, int y, int x_sub, int y_sub){
            this.x = x;
            this.y = y;
            this.x_sub = x_sub;
            this.y_sub = y_sub;
        }

        public boolean isEqual(int x, int y){
            return   this.x == x && this.y == y;
        }

        @Override
        public String toString() {
            return  x +"," + y + "," + x_sub +"," + y_sub ;
        }
    }

}
