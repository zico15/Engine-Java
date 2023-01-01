package game.components.properties;

import com.properties.components.Layouts;
import game.core.objects.GameObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

import java.util.function.Consumer;

public abstract class ComponentProperties <T> {

    private T pane;

    public Consumer<T> actionSet;

    public Consumer<T> actionFinished;





    public T getPane() {
        return pane;
    }

    public void setPane(T pane) {
        this.pane = pane;
    }

    public static AnchorPane addTitle(String title) {
        Label lt = new Label(title);
        lt.setAlignment(Pos.CENTER);
        lt.setMinHeight(25);
        Layouts.alignment(lt, Layouts.LEFT_RIGHT);
        AnchorPane pane = new AnchorPane(lt);
        pane.setStyle("-fx-background-color: #0093ff;");
        Layouts.alignment(pane, Layouts.LEFT_RIGHT);
        return pane;
    }

    public static VBox addName(GameObject gameObject, Consumer<TextField> action) {
        TextField name = new TextField(gameObject.getName());
        name.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (!name.getText().trim().isEmpty()) {
                gameObject.setName(name.getText().trim());
                action.accept(name);
               // baseGameComponentTree.setValue(gameObject.getName());
            }
        });
        VBox h1 = new VBox(addTitle("Name"), name);
        Layouts.alignment(h1, Layouts.LEFT_RIGHT);
        return  h1;
    }

    public static HBox addPositionXY(GameObject gameObject) {
        Label lx = new Label("X: ");
        lx.setMinWidth(20);
        lx.setTextAlignment(TextAlignment.CENTER);
        TextField x = new TextField(String.valueOf(gameObject.getVector().getX()));
        x.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (!x.getText().trim().isEmpty()) {
                try {
                    int t = Integer.valueOf(x.getText().trim());
                    gameObject.getVector().setX(t);
                } catch (Exception e) {
                    x.setText(String.valueOf(gameObject.getVector().getX()));
                }
            }
        });
        Label ly = new Label("Y: ");
        ly.setTextAlignment(TextAlignment.CENTER);
        ly.setMinWidth(20);
        TextField y = new TextField(String.valueOf(gameObject.getVector().getY()));
        y.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (!y.getText().trim().isEmpty()) {
                try {
                    int t = Integer.valueOf(y.getText().trim());
                    gameObject.getVector().setY(t);
                } catch (Exception e) {
                    y.setText(String.valueOf(gameObject.getVector().getY()));
                }
            }
        });
        HBox p1 = new HBox(lx, x, ly, y);
        HBox.setMargin(lx, new Insets(5, 5, 0, 0));
        HBox.setMargin(ly, new Insets(5, 3, 0, 5));
        return p1;
    }

    public static HBox addPositionWH(GameObject gameObject) {
        Label lw = new Label("W: ");
        lw.setMinWidth(20);
        lw.setTextAlignment(TextAlignment.CENTER);
        TextField w = new TextField(String.valueOf(gameObject.getVector().getWidth()));
        w.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (!w.getText().trim().isEmpty()) {
                try {
                    int t = Integer.valueOf(w.getText().trim());
                    gameObject.getVector().setWidth(t);
                } catch (Exception e) {
                    w.setText(String.valueOf(gameObject.getVector().getWidth()));
                }
            }
        });
        Label lh = new Label("H: ");
        lh.setTextAlignment(TextAlignment.CENTER);
        lh.setMinWidth(20);
        TextField h = new TextField(String.valueOf(gameObject.getVector().getHeight()));
        h.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (!h.getText().trim().isEmpty()) {
                try {
                    int t = Integer.valueOf(h.getText().trim());
                    gameObject.getVector().setHeight(t);
                } catch (Exception e) {
                    h.setText(String.valueOf(gameObject.getVector().getHeight()));
                }
            }
        });
        HBox p2 = new HBox(lw, w, lh, h);
        HBox.setMargin(lw, new Insets(5, 5, 0, 0));
        HBox.setMargin(lh, new Insets(5, 3, 0, 5));
       return  p2;
    }

    public static void addItem(Region node, VBox properties) {
        Layouts.alignment(node, Layouts.LEFT_RIGHT);
        properties.getChildren().add(node);
        properties.setMargin(node, new Insets(0, 0, 8, 0));
    }

    public static void addItemBack(Region node,  VBox properties) {
        Layouts.alignment(node, Layouts.LEFT_RIGHT);
        properties.getChildren().add(properties.getChildren().size() - 1, node);
        VBox.setMargin(node, new Insets(0, 0, 8, 0));
    }

}
