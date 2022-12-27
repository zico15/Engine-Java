package game.components.view.objects;

import com.tree.TreeBase;
import com.view.ComponentView;
import game.core.objects.GameObject;
import game.core.objects.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ScenePanel extends Canvas {

    private final Tab tab;

    private final GraphicsContext gc;

    private GameObject gameObject;

    public ScenePanel() {
        setId("gameScenePanel");
        tab = TreeBase.newTab("Scene", this);
        gc = getGraphicsContext2D();
        addEventHandler(MouseEvent.MOUSE_MOVED, evt -> {
        });
    }

    public void drawing() {
        gc.clearRect(0,0, getWidth(), getHeight());
        if (gameObject != null)
            ((Scene) getGameObject()).render(gc);
    }

    public Tab getTab() {
        return tab;
    }

    public MenuItem getMenuWindow() {
        MenuItem m = new MenuItem("Game Scene");
        m.setOnAction(e -> {
            TabPane pane = ComponentView.getComponent("tabPaneMain");
            if (!pane.getTabs().contains(getTab()))
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
