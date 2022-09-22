package com.properties;

import com.list.TextFieldNumber;
import com.tree.TreeItemObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

public abstract class PropertiesBase {

    public   VBox properties = null;
    public  TreeItemObject item;

    public static double NONE = -1;

    public PropertiesBase(TreeItemObject item){
        this.item = item;
    }

    public static void alignmentAll(Node parent){
        alignment(parent, 0,0,0,0);
    }
    public static  void alignment(Node parent, double top, double bottom, double left, double right)
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
    @NotNull
    public static final BorderPane newItem(String name, @NotNull Control parent, double height)
    {
        var l = new Label(name);
        l.setFocusTraversable(false);
        l.setAlignment(Pos.CENTER);
        l.setPadding(new Insets(5,5,5,10));
        parent.setPadding(new Insets(5,5,0,10));
        parent.setMinHeight(height);
        parent.setPrefWidth(0);
        parent.setMinWidth(0);
        alignmentAll(l);
        BorderPane h = new BorderPane();
        h.setPadding(new Insets(5,5,5,0));
        alignmentAll(h);
        alignment(parent, NONE, NONE, 0, 0);
        h.setLeft(l);
        h.setCenter(parent);
        return h;
    }

    @NotNull
    public static final Pane newItem(@NotNull Control parent_a, @NotNull Control parent_b, double height)
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

    @NotNull
    public  static final Node newItem(@NotNull Control parent, double height)
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

    @NotNull
    public static final Node itemTitle(@NotNull TreeItemObject item, double height)  {
        Label title = new Label(item.ob.getType().toUpperCase());
        title.setAlignment(Pos.CENTER);
        return newItem(title, height);
    }

    public static final Node itemLabel(String text, double height)  {
        Label title = new Label(text);
        title.setAlignment(Pos.CENTER);
        return newItem(title, height);
    }

    @NotNull
    public static final Node itemName(@NotNull TreeItemObject item, double height){
        TextField name = new TextField(item.ob.name);
        name.setOnKeyReleased(e -> {
            String n = name.getText().trim();
            item.ob.name = n;
            item.setValue(n);
        });
        name.setAlignment(Pos.TOP_LEFT);
        name.setFocusTraversable(false);
        alignment(name, NONE, NONE, 0, 0);
        return newItem("name", name, height);
    }

    public static Node itemSize(@NotNull TreeItemObject item, double height){
        VBox v = new VBox();
        alignment(v, NONE, NONE, 0, 0);
        Label position = new Label("Size");
        position.setAlignment(Pos.CENTER);
        position.setFocusTraversable(false);
        alignment(position, NONE, NONE, 0, 0);
        v.getChildren().add(newItem(position, height));
        TextFieldNumber w = new TextFieldNumber(item.ob.vector.getWidth(), Pos.CENTER);
        w.setOnKeyReleased(e -> { item.ob.vector.setWidth(Integer.parseInt(w.getText())); });
        TextFieldNumber h = new TextFieldNumber(item.ob.vector.getHeight(), Pos.CENTER);
        h.setOnKeyReleased(e -> { item.ob.vector.setHeight(Integer.parseInt(h.getText())); });
        v.getChildren().add(newItem("W:", w , 20));
        v.getChildren().add(newItem("H: ", h , 20));
        return (v);
    }
    @NotNull
    public static Node itemPosition(@NotNull TreeItemObject item, double height){
        VBox v = new VBox();
        alignment(v, NONE, NONE, 0, 0);
        Label position = new Label("Position");
        position.setAlignment(Pos.CENTER);
        position.setFocusTraversable(false);
        alignment(position, NONE, NONE, 0, 0);
        v.getChildren().add(newItem(position, height));
        TextFieldNumber x = new TextFieldNumber(item.ob.vector.getX(), Pos.CENTER);
        x.setOnKeyReleased(e -> { item.ob.vector.setX(Integer.parseInt(x.getText())); });
        TextFieldNumber y = new TextFieldNumber(item.ob.vector.getY(), Pos.CENTER);
        y.setOnKeyReleased(e -> { item.ob.vector.setY(Integer.parseInt(y.getText())); });
        TextFieldNumber w = new TextFieldNumber(item.ob.vector.getWidth(), Pos.CENTER);
        w.setOnKeyReleased(e -> { item.ob.vector.setWidth(Integer.parseInt(w.getText())); });
        TextFieldNumber h = new TextFieldNumber(item.ob.vector.getHeight(), Pos.CENTER);
        h.setOnKeyReleased(e -> { item.ob.vector.setHeight(Integer.parseInt(h.getText())); });
        v.getChildren().add(newItem("X: ", x , 20));
        v.getChildren().add(newItem("Y: ", y , 20));
        v.getChildren().add(newItem("W:", w , 20));
        v.getChildren().add(newItem("H: ", h , 20));
        return v;
    }

    public abstract void createProperties();

    public final void addItemProperties(Node node)  {
        if (node != null)
            getProperties().getChildren().add(node);
    }

    public final void addItemProperties(int index, Node node) {
        if (node != null)
            getProperties().getChildren().add(index, node);
    }

    public final int getSize()   {
        return getProperties().getChildren().size();
    }
    public final VBox getProperties() {
        if (properties != null)
            return properties;
        properties = new VBox();
        alignmentAll(properties);
        createProperties();
        return properties;
    }

    public void onMouseMove(MouseEvent e){

    }

    public void onMouseClick(MouseEvent e){

    }
}
