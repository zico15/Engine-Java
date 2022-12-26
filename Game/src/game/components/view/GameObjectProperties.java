package game.components.view;

import com.properties.components.Layouts;
import com.tree.TreeBase;
import game.components.properties.ImageProperties;
import game.components.tree.BaseGameComponentTree;
import game.core.components.Sprite;
import game.core.objects.GameObject;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class GameObjectProperties extends AnchorPane {


    private static GameObjectProperties properties;
    private GameObject gameObject;
    private BaseGameComponentTree baseGameComponentTree;
    private Tab tab;

    private VBox vBox;
    private TabPane tabPaneMain;

    public GameObjectProperties(TabPane tabPaneMain) {
        setTab(TreeBase.newTab("Properties", this));
        this.tabPaneMain = tabPaneMain;
        GameObjectProperties.properties = this;
    }

    public static void load(BaseGameComponentTree baseGameComponentTree)
    {
        load(baseGameComponentTree, true);
    }
    public static void load(BaseGameComponentTree baseGameComponentTree, Boolean isEditable)
    {
        if (GameObjectProperties.properties != null)
        {
            GameObjectProperties.properties.getChildren().clear();
            if (!GameObjectProperties.properties.tabPaneMain.getTabs().contains(GameObjectProperties.properties.getTab()))
                GameObjectProperties.properties.tabPaneMain.getTabs().add(GameObjectProperties.properties.getTab());
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
        if (gameObject == null )
            return;
        addName();
        if (!isEditable)
            return;
        addTitle("  Position");
        addPositionXY();
        addPositionWH();
        gameObject.getComponents().forEach(c -> {
            if (c instanceof Sprite)
                properties.addItem( new ImageProperties(this, (Sprite) c));
        });
        createProperties();
    }

    private void addTitle(String title)
    {
        Label lt = new Label(title);
        lt.setTextAlignment(TextAlignment.CENTER);
        lt.setMinHeight(25);
        Pane pane = new Pane(lt);
        pane.setStyle("-fx-background-color: #0093ff;");
        addItem(pane);
    }
    private void addName()
    {
        TextField name = new TextField(gameObject.getName());
        name.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if(!name.getText().trim().isEmpty()) {
                gameObject.setName(name.getText().trim());
                baseGameComponentTree.setValue(gameObject.getName());
            }
        });
        addTitle("  Name");
        addItem(name);
    }

    private void addPositionXY(){
        Label lx = new Label("X: ");
        lx.setMinWidth(20);
        lx.setTextAlignment(TextAlignment.CENTER);
        TextField x = new TextField(String.valueOf(gameObject.getVector().getX()));
        x.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if(!x.getText().trim().isEmpty()) {
                try {
                    int t = Integer.valueOf(x.getText().trim());
                    gameObject.getVector().setX(t);
                } catch (Exception e){
                    x.setText(String.valueOf(gameObject.getVector().getX()));
                }
            }
        });
        Label ly = new Label("Y: ");
        ly.setTextAlignment(TextAlignment.CENTER);
        ly.setMinWidth(20);
        TextField y = new TextField(String.valueOf(gameObject.getVector().getY()));
        y.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if(!y.getText().trim().isEmpty()) {
                try {
                    int t = Integer.valueOf(y.getText().trim());
                    gameObject.getVector().setY(t);
                } catch (Exception e){
                    y.setText(String.valueOf(gameObject.getVector().getY()));
                }
            }
        });
        HBox p1 = new HBox(lx, x, ly, y);
        p1.setMargin(lx, new Insets(5, 5, 0, 0));
        p1.setMargin(ly, new Insets(5, 3, 0, 5));
        addItem(p1);
    }
    private void addPositionWH(){
        Label lw = new Label("W: ");
        lw.setMinWidth(20);
        lw.setTextAlignment(TextAlignment.CENTER);
        TextField w = new TextField(String.valueOf(gameObject.getVector().getWidth()));
        w.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if(!w.getText().trim().isEmpty()) {
                try {
                    int t = Integer.valueOf(w.getText().trim());
                    gameObject.getVector().setWidth(t);
                } catch (Exception e){
                    w.setText(String.valueOf(gameObject.getVector().getWidth()));
                }
            }
        });
        Label lh = new Label("H: ");
        lh.setTextAlignment(TextAlignment.CENTER);
        lh.setMinWidth(20);
        TextField h = new TextField(String.valueOf(gameObject.getVector().getHeight()));
        h.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if(!h.getText().trim().isEmpty()) {
                try {
                    int t = Integer.valueOf(h.getText().trim());
                    gameObject.getVector().setHeight(t);
                } catch (Exception e){
                    h.setText(String.valueOf(gameObject.getVector().getHeight()));
                }
            }
        });
        HBox p2 = new HBox(lw, w, lh, h);
        p2.setMargin(lw, new Insets(5, 5, 0, 0));
        p2.setMargin(lh, new Insets(5, 3, 0, 5));
        addItem(p2);
    }
    private void addItem(Region node){
        Layouts.alignment(node, Layouts.LEFT_RIGHT);
        vBox.getChildren().add(node);
        vBox.setMargin(node, new Insets(0, 0, 8, 0));
    }

    public void addItemBack(Region node){
        Layouts.alignment(node, Layouts.LEFT_RIGHT);
        vBox.getChildren().add(vBox.getChildren().size() -  1, node);
        vBox.setMargin(node, new Insets(0, 0, 8, 0));
    }

    public void createProperties() {
        MenuButton menu = new MenuButton("Components");
        menu.setAlignment(Pos.CENTER);
        menu.setPopupSide(Side.RIGHT);
        menu.getStyleClass().add("my-menuButton");
        menu.getItems().add(ImageProperties.creatingProperties(this));
        /*menu.getItems().add(newMenuItem("Scrips", e-> {
            properties.getChildren().add(properties.getSize() - 1, new ScriptProperties(properties));
        }));*/
        // alignment(menu, NONE, NONE, 0, 0);
        Layouts.alignment(menu, Layouts.ALL);
        AnchorPane pane = new AnchorPane(menu);
        addItem(pane);
    }
    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}