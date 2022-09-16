package com.properties;

import com.list.TextFieldNumber;
import com.tree.TreeItemObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PropertiesBase {

    protected   AnchorPane properties;
    protected  TreeItemObject item;

    public static double NONE = -1;

    public PropertiesBase(TreeItemObject item){
        this.item = item;
    }

    public void alignmentAll(Node parent){
        alignment(parent, 0,0,0,0);
    }
    public  void alignment(Node parent, double top, double bottom, double left, double right)
    {
        if (top >= 0)
            AnchorPane.setTopAnchor(parent, top);
        if (bottom >= 0)
            AnchorPane.setBottomAnchor(parent, bottom);
        if (left >= 0)
            AnchorPane.setLeftAnchor(parent, left);
        if (right >= 0)
            AnchorPane.setRightAnchor(parent, right);
    }
    public BorderPane newItem(String name, Control parent, double height)
    {
        var l = new Label(name);
        l.setFocusTraversable(false);
        l.setAlignment(Pos.CENTER_RIGHT);
        l.setPadding(new Insets(0,5,0,0));
        parent.setPadding(new Insets(0,5,0,0));
        l.setMinHeight(height);
        parent.setMinHeight(height);
        parent.setPrefWidth(0);
        parent.setMinWidth(0);
        BorderPane h = new BorderPane();
        alignmentAll(h);
        alignmentAll(parent);
        h.setLeft(l);
        h.setCenter(parent);
        return h;
    }

    public Pane newItem(Control parent_a, Control parent_b, double height)
    {
        parent_a.setMinHeight(height);
        parent_a.setPrefWidth(0);
        parent_a.setMinWidth(0);
        alignmentAll(parent_a);
        parent_b.setMinHeight(height);
        parent_b.setPrefWidth(0);
        parent_b.setMinWidth(0);
        alignmentAll(parent_b);
        BorderPane h = new BorderPane();
        alignmentAll(h);
        h.setLeft(parent_a);
        h.setRight(parent_b);
        return h;
    }

    public  Node newItem(Control parent, double height)
    {
        AnchorPane panel = new AnchorPane();
        panel.setStyle("-fx-background-color: #0093ff;");
        parent.setMinHeight(height);
        alignmentAll(parent);
        alignmentAll(panel);
        parent.setPrefWidth(0);
        parent.setMinWidth(0);
        panel.getChildren().add(parent);
        parent.setFocusTraversable(false);
        panel.setFocusTraversable(false);
        return panel;
    }

    public Node itemTitle(double height)  {
        Label title = new Label(item.ob.type.toUpperCase());
        title.setAlignment(Pos.CENTER);
        return newItem(title, height);
    }

    public Node itemName(double height){
        TextField name = new TextField(item.ob.name);
        name.setOnKeyReleased(e -> {
            String n = name.getText().trim();
            item.ob.name = n;
            item.setValue(n);
        });
        name.setFocusTraversable(false);
        return newItem("name", name, height);
    }

    public Node itemPosition(double height){
        VBox v = new VBox();
        alignment(v, NONE, NONE, 0, 0);
        Label position = new Label("Position");
        position.setAlignment(Pos.CENTER);
        position.setFocusTraversable(false);
        alignment(position, NONE, NONE, 0, 0);
        v.getChildren().add(newItem(position, height));
        TextFieldNumber x = new TextFieldNumber(item.ob.vector.x, Pos.CENTER);
        x.setOnAction(e -> { item.ob.vector.x = Integer.parseInt(x.getText()); });
        TextFieldNumber y = new TextFieldNumber(item.ob.vector.y, Pos.CENTER);
        y.setOnAction(e -> { item.ob.vector.y = Integer.parseInt(y.getText()); });
        TextFieldNumber w = new TextFieldNumber(item.ob.vector.width, Pos.CENTER);
        w.setOnAction(e -> { item.ob.vector.width = Integer.parseInt(w.getText()); });
        TextFieldNumber h = new TextFieldNumber(item.ob.vector.height, Pos.CENTER);
        h.setOnAction(e -> { item.ob.vector.height = Integer.parseInt(h.getText()); });
        v.getChildren().add(newItem("X: ", x , 20));
        v.getChildren().add(newItem("Y: ", y , 20));
        v.getChildren().add(newItem("W: ", w , 20));
        v.getChildren().add(newItem("H: ", h , 20));
        return v;
    }

    public Node getProperties() {
        return properties;
    }

}
