package com;

import com.canva.CanvaView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController extends Control {

    CanvaView canva = new CanvaView();
    @FXML
    private AnchorPane panelProject;
    @FXML
    private TabPane tabPaneMain;
    private GraphicsContext gc;

    @FXML
    private void initialize() {
        System.out.println("initialize");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/treeView/tree-view.fxml"));
            Node t = loader.load();
            AnchorPane.setTopAnchor(t, 30.0);
            AnchorPane.setBottomAnchor(t, 0.0);
            AnchorPane.setLeftAnchor(t, 0.0);
            AnchorPane.setRightAnchor(t, 0.0);
            panelProject.getChildren().add(t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AnchorPane panel = new AnchorPane();
        AnchorPane.setTopAnchor(canva, 0.0);
        AnchorPane.setBottomAnchor(canva, 0.0);
        AnchorPane.setLeftAnchor(canva, 0.0);
        AnchorPane.setRightAnchor(canva, 0.0);
        panel.getChildren().add(canva);
        tabPaneMain.getTabs().add(new Tab("Scene", panel));

    }

    @FXML
    protected void onHelloButtonClick() {

        canva.drawLines();
    }
}