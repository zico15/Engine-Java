package com.engine.system;

import com.assets.image.Images;

import com.engine.graphics.Graphics;
import com.engine.objects.map.Map;
import com.engine.objects.player.Player;
import com.engine.system.events.EventKeys;
import com.engine.system.events.EventMouse;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Run extends Application {


    private GameLoop gameLoop;
    private Graphics graphics;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Windows.stage = primaryStage;
        // evento de fechar a janela
        primaryStage.setOnCloseRequest(event -> GameLoop.exit());
        primaryStage.setTitle("Game");
        primaryStage.setScene(initScene());
        primaryStage.show();
        initGame();
    }



    private Scene initScene(){
        var canvas = new Canvas(800, 600);
        canvas.autosize();
      
        graphics = new Graphics(canvas.getGraphicsContext2D());
        AnchorPane.setBottomAnchor(canvas, 0.0);
        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);
        AnchorPane.setRightAnchor(canvas, 0.0);
        Scene scene = new Scene(new AnchorPane(canvas));
        // Events
        EventKeys.initEvent(scene);
        EventMouse.initEvent(scene);
         scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> {
             canvas.setWidth(newWidth.doubleValue());
         });
         scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> {
             canvas.setHeight(newHeight.doubleValue());
         });
         return  scene;
    }


    public void initGame(){
        com.engine.objects.scene.Scene scene = new com.engine.objects.scene.Scene();
        scene.add(new Map());
        scene.add(new Player());
        gameLoop = new GameLoop(scene, graphics);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    gameLoop.start();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        };
        new Thread(task).start();


    }
}
