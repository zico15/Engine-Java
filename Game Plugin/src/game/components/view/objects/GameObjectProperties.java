package game.components.view.objects;

import com.properties.components.Layouts;
import com.tree.TreeBase;
import game.components.properties.ComponentProperties;
import game.components.properties.ImageProperties;
import game.components.properties.ScriptProperties;
import game.components.tree.base.BaseGameComponentTree;
import game.core.components.Sprite;
import game.core.objects.GameObject;
import game.core.objects.Scene;
import game.project.GameEngine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class GameObjectProperties extends AnchorPane {

    public static GameObjectProperties properties;
    private GameObject gameObject;
    private BaseGameComponentTree baseGameComponentTree;
    private Tab tabView;

    private VBox vBox;
    private final TabPane tabPaneMain;



    private ArrayList<Region> componentsProperties = new ArrayList<>();

    public GameObjectProperties(TabPane tabPaneMain) {

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFocusTraversable(false);
        this.setFocused(false);
        setFocusTraversable(false);
        Layouts.alignment(this, Layouts.LEFT_RIGHT);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(this);
        setTabView(TreeBase.newTab("Properties", scrollPane));
        this.tabPaneMain = tabPaneMain;
        GameObjectProperties.properties = this;
    }

    public static void load(BaseGameComponentTree baseGameComponentTree) {
        load(baseGameComponentTree, true);
    }

    public static void load(BaseGameComponentTree baseGameComponentTree, Boolean isEditable) {
        if (GameObjectProperties.properties != null) {
            GameEngine.scene.setGameObject(baseGameComponentTree.getGameObject());
            GameObjectProperties.properties.getChildren().clear();
            GameObjectProperties.properties.componentsProperties.clear();
            if (!GameObjectProperties.properties.tabPaneMain.getTabs().contains(GameObjectProperties.properties.getTabView()))
                GameObjectProperties.properties.tabPaneMain.getTabs().add(GameObjectProperties.properties.getTabView());
            GameObjectProperties.properties.vBox = new VBox();
            GameObjectProperties.properties.vBox.setPadding(new Insets(5.0));
            Layouts.alignment(GameObjectProperties.properties.vBox, Layouts.ALL);
            // AnchorPane a = new AnchorPane(GameObjectProperties.properties.vBox);
            // Layouts.alignment(a, Layouts.ALL);
            //ScrollPane scrollPane = new ScrollPane(a);
            // Layouts.alignment(scrollPane, Layouts.ALL);
            // scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
            GameObjectProperties.properties.getChildren().add(GameObjectProperties.properties.vBox);
            GameObjectProperties.properties.loadProperties(baseGameComponentTree, isEditable);
        }
    }

    private void loadProperties(BaseGameComponentTree baseGameComponentTree, Boolean isEditable) {
        this.baseGameComponentTree = baseGameComponentTree;
        this.gameObject = baseGameComponentTree.getGameObject();
        if (gameObject == null)
            return;
        addName();
        if (!(baseGameComponentTree.getGameObject()  instanceof Scene))
        {
            addTitle("  Position");
            addPositionXY();
            addPositionWH();
        }
        if (isEditable) {
            gameObject.getComponents().forEach(c -> {
                if (c instanceof Sprite)
                    properties.addItem(new ImageProperties((Sprite) c).getView());

            });
            createProperties();
        }
    }

    public static <T> List<T> getComponents(Class<T> component){
        List<T> tList = new ArrayList<>();
        for (Node r : GameObjectProperties.properties.getChildren()) {
            System.out.println(r.getClass().getSimpleName());
            if (r.getClass().equals(component))
                tList.add((T) r);
        }
        return tList;
    }

    public void addTitle(String title) {
        Label lt = new Label(title);
        lt.setAlignment(Pos.CENTER);
        lt.setMinHeight(25);
        Layouts.alignment(lt, Layouts.LEFT_RIGHT);
        AnchorPane pane = new AnchorPane(lt);
        pane.setStyle("-fx-background-color: #0093ff;");

        addItem(pane);
    }

    private void addName() {
        TextField name = new TextField(gameObject.getName());
        name.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (!name.getText().trim().isEmpty()) {
                gameObject.setName(name.getText().trim());
                baseGameComponentTree.setValue(gameObject.getName());
            }
        });
        addTitle("Name");
        addItem(name);
    }

    private void addPositionXY() {
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
        addItem(p1);
    }

    private void addPositionWH() {
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
        addItem(p2);
    }

    public static void addItem(Region node) {
        Layouts.alignment(node, Layouts.LEFT_RIGHT);
        GameObjectProperties.properties.vBox.getChildren().add(node);
        GameObjectProperties.properties.vBox.setMargin(node, new Insets(0, 0, 8, 0));
    }

    public void addItemBack(Region node) {
        Layouts.alignment(node, Layouts.LEFT_RIGHT);
        vBox.getChildren().add((vBox.getChildren().size()  - 1) , node);
        VBox.setMargin(node, new Insets(0, 0, 8, 0));
    }

    public void createProperties() {
        addItem(ComponentProperties.createPropertiesMenu());
    }

    public Tab getTabView() {
        return tabView;
    }

    public void setTabView(Tab tabView) {
        this.tabView = tabView;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

}
