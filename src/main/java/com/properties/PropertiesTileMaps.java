package com.properties;

import com.MainViewController;
import com.canva.CanvasView;
import com.canva.ImageFX;
import com.system.FileSistem;
import com.tree.TreeItemObject;
import engine2d.components.Sprite;
import engine2d.objects.TileMaps;
import engine2d.transforme.Vector2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import static com.properties.PropertiesItem.*;

public class PropertiesTileMaps extends PropertiesBase {

    private Vector2D vector2D = new Vector2D();
    private AtomicReference<GraphicsContext> gc;

    private ImageFX imageFX;
    private CanvasView canvas;
    private TileMaps tileMaps;

    public PropertiesTileMaps(TreeItemObject item){
        super(item);
        vector2D.setWidth(32);
        vector2D.setHeight(32);
    }


    @Override
    public void createProperties() {
        System.out.println("PropertiesTileMaps");
        getChildren().add(tileMap());
    }

    private BorderPane tileMap(){
        tileMaps = (TileMaps) getItem().ob;
        BorderPane list = new BorderPane();
        alignmentAll(list);
        list.setFocusTraversable(false);
        Canvas canvas = new Canvas();
        canvas.setOnMouseClicked(e -> selectImage(e));
        gc = new AtomicReference<>(canvas.getGraphicsContext2D());
        this.canvas = MainViewController.canvas;
        ScrollPane scrollPane = new ScrollPane(canvas);
        alignmentAll(scrollPane);
        list.setCenter(scrollPane);
        TextField img = new TextField();
        img.setEditable(false);
        img.setOnMouseClicked(e -> {
            File file = FileSistem.openFile();
            if (file == null || file.isDirectory())
                return;
            tileMaps.setSprite(file);
            Sprite sprite = tileMaps.getSprite();
            if (sprite == null)
                return;
            initSprite(sprite, img, canvas);
        });
        if (tileMaps.getSprite() != null && tileMaps.getSprite().getFile() != null){
            tileMaps.getSprite().load(tileMaps.getSprite().getFile());
            initSprite(tileMaps.getSprite(), img, canvas);
        }
        VBox v = new VBox();
        v.setPrefWidth(0);
        v.setMinWidth(0);
        v.getChildren().addAll(itemTitle(getItem(),20), itemName(getItem(),20), PropertiesItem.itemSize(getItem(), 20), newItem("Image", img, 20));
        list.setTop(v);
        return list;
    }

    private void initSprite(Sprite sprite, TextField img, Canvas canvas){
        imageFX = new ImageFX(sprite);
        img.setText(sprite.getFile().toString());
        System.out.println("w: "+sprite.getWidth() + " h: "+sprite.getHeight());
        if (sprite.getWidth() > 0)
            canvas.setWidth(sprite.getWidth());
        if (sprite.getHeight() > 0)
            canvas.setHeight(sprite.getHeight());
        canvas.getGraphicsContext2D();
        gc.get().clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.get().drawImage(imageFX, 0,1);
    }

    private void selectImage(MouseEvent e){
        int x = (int)(e.getX() / 32) * 32;
        int y = (int)(e.getY() / 32) * 32;
        vector2D.setX(x);
        vector2D.setY(y);
        gc.get().clearRect(0,0, tileMaps.getSprite().getWidth(), tileMaps.getSprite().getHeight());
        gc.get().drawImage(imageFX, 0,1);
        gc.get().setStroke(Color.GREEN);
        gc.get().strokeRoundRect(x, y, 32, 32, 5, 5);
        gc.get().stroke();
    }

    @Override
    public void onMouseMove(MouseEvent e) {
        if (tileMaps.getBuffer() == null)
            return;
        int x = (int)(e.getX() / 32) * 32;
        int y = (int)(e.getY() / 32) * 32;
        if (x + 32 > tileMaps.vector.getWidth() || y + 32 > tileMaps.vector.getHeight())
            return;
        //canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        canvas.render();
        canvas.getGraphicsContext2D().setStroke(Color.GREEN);
        canvas.getGraphicsContext2D().strokeRoundRect(x, y, vector2D.getWidth(), vector2D.getHeight(), 5, 5);
    }

    @Override
    public void onMouseClick(MouseEvent e) {
        int x = (int)(e.getX() / 32) * 32;
        int y = (int)(e.getY() / 32) * 32;
       if (tileMaps.getBuffer() != null && tileMaps.getBuffer() != null && tileMaps.getBuffer().getWidth() > x && tileMaps.getBuffer().getHeight() > y && tileMaps.getSprite() != null)
        {

            tileMaps.print(tileMaps.getSprite().getBuffer(), vector2D, x, y);
            canvas.render();
        }
    }


}
