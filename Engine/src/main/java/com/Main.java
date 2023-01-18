package com;

import com.view.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main  extends Application {



    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        MainViewController.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.setOnKeyPressed(e -> System.out.println(e.getCode().getChar()));
        stage.setTitle("Game!");
        stage.setScene(scene);
        stage.show();
    }

}