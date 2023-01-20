package com.view;

import com.Main;
import com.properties.components.Layouts;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class DialogPane
{

    private final Stage stage;

    private AnchorPane anchorPane;

    /***
     *  initModality(Modality.APPLICATION_MODAL);
     *  initOwner(MainViewController.stage);
     * ***/
    public DialogPane(String title){
        stage = new Stage();
        initModality(Modality.APPLICATION_MODAL);
        initOwner(MainViewController.stage);
        stage.setTitle(title);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dialog-pane.fxml"));
        try {
            anchorPane = fxmlLoader.load();
            stage.setScene(new Scene(anchorPane));
        } catch (IOException e) {

        }
        stage.setOnCloseRequest(event -> System.out.println("close"));
    }

    public DialogPane(String title, Pane pane){
        this(title);
        setPane(pane);
    }

    public void close(){
        if(stage.getOnCloseRequest() != null)
            stage.getOnCloseRequest().handle(null);
        stage.close();
    }

    public  void setPane(Pane pane){
        Layouts.alignment(pane, Layouts.ALL);
        anchorPane.setPrefSize(pane.getPrefWidth(), pane.getPrefHeight());
        anchorPane.getChildren().setAll(pane);
    }

    public void  setOnCloseRequest(Consumer<Parent> value){
        stage.setOnCloseRequest(e -> value.accept(stage.getScene().getRoot()));
    }

    public void initOwner(Window owner){
        stage.initOwner(owner);
    }

    public void initModality(Modality modality){
        stage.initModality(modality);
    }


    public  void showAndWait(Consumer<String> e) {
        stage.showAndWait();
    }



    public  static class ProgressBarPane extends BorderPane {

        private final ProgressBar progressBar;
        private final Button button;

        public ProgressBarPane(DialogPane dialogPane){
            progressBar = new ProgressBar(100);
            Layouts.alignment(progressBar, Layouts.ALL, 20);
            setCenter(progressBar);
            button = new Button("OK");
            button.setOnAction(e -> dialogPane.close());
            button.setDisable(true);
            setBottom(new BorderPane(button));
            new BorderPane(new ProgressBar(), null, null, null, null);
            progressBar.setProgress(0);

           // progressBar.setViewOrder(100);
        }



        public void run(Consumer<ProgressBar> consumer){
           // Thread t = new Thread(() -> {
                Platform.runLater(() -> {
                    Thread t = new Thread(() -> {
                        while (progressBar.getProgress() < 1)
                            consumer.accept(progressBar);
                        button.setDisable(false);
                    });
                    t.start();
                });
           // });
           // t.start();
        }
    }
}
