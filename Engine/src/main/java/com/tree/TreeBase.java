package com.tree;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class TreeBase {

    public static Tab newTab(String name, Node node) {
        AnchorPane panel = new AnchorPane();
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        panel.getChildren().add(node);
        node.setFocusTraversable(false);
        Tab tab = new Tab(name, panel);
        tab.setClosable(true);
        return (tab);
    }

    public static Tab newTab(String name, Canvas canvas) {
        AnchorPane panel = new AnchorPane();
        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setBottomAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);
        AnchorPane.setRightAnchor(canvas, 0.0);
        panel.getChildren().add(canvas);
        canvas.widthProperty().bind(panel.widthProperty());
        canvas.heightProperty().bind(panel.heightProperty());
        canvas.setFocusTraversable(false);
        Tab tab = new Tab(name, panel);
        tab.setClosable(true);
        return (tab);
    }
}
