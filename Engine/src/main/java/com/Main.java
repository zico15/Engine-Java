package com;

import com.view.DialogPane;
import com.view.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
        Parent p = fxmlLoader.load();

        Scene scene = new Scene(p, 800, 600);
        scene.setOnKeyPressed(e -> System.out.println(e.getCode().getChar()));
        stage.setTitle("Game!");
        stage.setScene(scene);
        stage.show();
        Button b = new Button("close");

        var d = new  DialogPane("DialogPane");
        DialogPane.ProgressBarPane progressBarPane = new DialogPane.ProgressBarPane(d);
        d.setPane(progressBarPane);
        progressBarPane.run(e -> {
            System.out.println(e.getProgress());
            e.setProgress(e.getProgress() + 0.0000001f);
        });
        d.showAndWait(e -> {
            System.out.println("select: " + e);
        });

    }

}