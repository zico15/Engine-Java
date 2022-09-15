package com.list;

import com.tree.TreeItemObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PropertiesBase {


    private static ListView<Node> newList(){
        ListView<Node> list = new ListView<>();
        //list.setSelectionModel(null);
        alignment(list);
        return list;
    }

    private static void alignment(Node parent)
    {
        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
    }
    public static BorderPane newItem(String name, Control parent, double height)
    {
        var l = new Label(name);
        l.setAlignment(Pos.CENTER_RIGHT);
        l.setPadding(new Insets(0,5,0,0));
        parent.setPadding(new Insets(0,5,0,0));
        l.setMinHeight(height);
        parent.setMinHeight(height);
        parent.setPrefWidth(0);
        parent.setMinWidth(0);
        BorderPane h = new BorderPane();
        alignment(h);
        alignment(parent);
        h.setLeft(l);
        h.setCenter(parent);
        return h;
    }

    public static  Node newItem(Control parent, double height)
    {
        AnchorPane panel = new AnchorPane();
        parent.setMinHeight(height);
        alignment(parent);
        alignment(panel);
        parent.setPrefWidth(0);
        parent.setMinWidth(0);
        panel.getChildren().add(parent);
        return panel;
    }

    public static ListView<Node>  tileMap(TreeItemObject item){
        ListView<Node> list = object(item);
        return list;
    }
    public static ListView<Node>  object(TreeItemObject item){
        ListView<Node> list = newList();
        list.setFocusTraversable(false);
        Label title = new Label(item.ob.type.toUpperCase());
        title.setAlignment(Pos.CENTER);

        TextField name = new TextField(item.ob.name);
        name.setOnKeyReleased(e -> {
            String n = name.getText().trim();
            item.ob.name = n;
            item.setValue(n);
        });
        name.setFocusTraversable(false);
        Label position = new Label("Position");
        position.setAlignment(Pos.CENTER);
        position.setFocusTraversable(false);
        list.getItems().add(newItem(title, 20));
        list.getItems().add(newItem("name", name, 20));
        list.getItems().add(newItem(position, 20));
        list.getItems().add(newItem("X: ", new TextField("0"), 20));
        list.getItems().add(newItem("Y: ", new TextField("0"), 20));
        list.getItems().add(newItem("W:",new TextField("0"), 20));
        list.getItems().add(newItem("H: ",new TextField("0"), 20));
        return list;
    }
}
