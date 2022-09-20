package com.properties;

import com.MainViewController;
import com.canva.CanvasView;
import com.system.FileSistem;
import com.tree.TreeItemObject;
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

public class PropertiesTileMaps extends PropertiesBase{

    private Vector2D vector2D = new Vector2D();
    private AtomicReference<GraphicsContext> gc;

    private CanvasView canvas;
    private TileMaps tileMaps;

    public PropertiesTileMaps(TreeItemObject item){
        super(item);
    }

    @Override
    public void createProperties() {
        System.out.println("PropertiesTileMaps");
        addItemProperties(tileMap());
    }

    private BorderPane tileMap(){
        tileMaps = (TileMaps) item.ob;
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
        TextField img = new TextField(tileMaps.getFile());
        img.setEditable(false);
        img.setOnMouseClicked(e -> {
            File file = FileSistem.openFile();
            if (file == null || file.isDirectory())
                return;
            tileMaps.setImage(file);
            img.setText(tileMaps.getFile());
            if (tileMaps.getImage() == null)
                return;
            canvas.setWidth(tileMaps.getImage().getWidth());
            canvas.setHeight(tileMaps.getImage().getHeight());
            canvas.getGraphicsContext2D();
            tileMaps.setImage(FileSistem.getImage(tileMaps.fileImage));
            gc.get().clearRect(0,0,canvas.getWidth(), canvas.getHeight());
            gc.get().drawImage(tileMaps.getImage(), 0,1);
            System.out.println(tileMaps.fileImage.getAbsolutePath());
        });
        VBox v = new VBox();
        v.setPrefWidth(0);
        v.setMinWidth(0);
        v.getChildren().addAll(itemTitle(item,20), itemName(item,20), PropertiesBase.itemSize(item, 20), newItem("Image", img, 20));
        list.setTop(v);
        vector2D.setWidth(32);
        vector2D.setHeight(32);
        return list;
    }

    private void selectImage(MouseEvent e){
        int x = (int)(e.getX() / 32) * 32;
        int y = (int)(e.getY() / 32) * 32;
        vector2D.setX(x);
        vector2D.setY(y);
        gc.get().clearRect(0,0, tileMaps.getImage().getWidth(), tileMaps.getImage().getHeight());
        gc.get().drawImage(tileMaps.getImage(), 0,1);
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
        if (x + 32 > tileMaps.getBuffer().getWidth() || y + 32 > tileMaps.getBuffer().getHeight())
            return;
        canvas.render();
        canvas.getGraphics().setStroke(Color.GREEN);
        canvas.getGraphics().strokeRoundRect(x, y, 32, 32, 5, 5);
    }

    @Override
    public void onMouseClick(MouseEvent e) {
        int x = (int)(e.getX() / 32) * 32;
        int y = (int)(e.getY() / 32) * 32;
        if (tileMaps.getImage() != null && tileMaps.getBuffer() != null && tileMaps.getBuffer().getWidth() > x && tileMaps.getBuffer().getHeight() > y)
        {
            System.out.println("onMouseClick");
            tileMaps.print(tileMaps.getImage(), vector2D, x, y);
            canvas.render();
        }
    }


}
