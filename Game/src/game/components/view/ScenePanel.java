package game.components.view;

import com.tree.TreeBase;
import com.view.ComponentView;
import game.core.objects.GameObject;
import game.core.objects.Scene;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ScenePanel extends Canvas {

    private Tab tab;

    private GraphicsContext gc;

    private GameObject gameObject;
    public ScenePanel() {
        setId("gameScenePanel");
        tab = TreeBase.newTab("Scene", this);
        gc = getGraphicsContext2D();
        addEventHandler(MouseEvent.MOUSE_MOVED, evt ->  {
            gc.setFill(Color.AZURE);
            gc.fillRect(evt.getX(), evt.getY(), 10, 10);
        });
    }

    public void drawing() {
        gc.setStroke(Color.FORESTGREEN.brighter());
        gc.setLineWidth(5);
        gc.strokeOval(30, 30, 80, 80);
        gc.setFill(Color.FORESTGREEN);
        gc.fillOval(130, 30, 80, 80);
        ((Scene) getGameObject()).render(gc);
    }
    public Tab getTab(){
        return tab;
    }
    public MenuItem getMenuWindow()
    {
        MenuItem m = new MenuItem("Game Scene");
        m.setOnAction(e -> {
            TabPane pane = ComponentView.getComponent("tabPaneMain");
            if(!pane.getTabs().contains(getTab()))
                pane.getTabs().add(getTab());
            System.out.println("menuOn: scene");
        });
        System.out.println("menu: add");
        return m;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
