package com.properties.components;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.NotNull;

public class Layouts {

    public static final int TOP = 1, BOTTOM = 2, LEFT = 3 , RIGHT = 4, LEFT_RIGHT = 6, TOP_BOTTOM = 7, ALL = 8;

    public static void alignment(Node node, int i)
    {
        alignment(node, i, 0.0);
    }
    public static void alignment(Node node, int i, double a)
    {
        if (i == LEFT || i == LEFT_RIGHT || i == ALL)
            AnchorPane.setLeftAnchor(node, a);
        if (i == RIGHT || i == LEFT_RIGHT || i == ALL)
            AnchorPane.setRightAnchor(node, a);
        if (i == BOTTOM || i == TOP_BOTTOM || i == ALL)
            AnchorPane.setBottomAnchor(node, a);
        if (i == TOP || i == TOP_BOTTOM || i == ALL)
            AnchorPane.setTopAnchor(node, a);
    }

    public  static final Region newItem(Region parent, double height)
    {
        AnchorPane panel = new AnchorPane();
        panel.setStyle("-fx-background-color: #0093ff;");
        alignment(parent, ALL);
        parent.setMinHeight(height);
        parent.setPrefWidth(0);
        parent.setMinWidth(0);
        panel.getChildren().add(parent);
        parent.setFocusTraversable(false);
        panel.setFocusTraversable(false);
        return panel;
    }
}
