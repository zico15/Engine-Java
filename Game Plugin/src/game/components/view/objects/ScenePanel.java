package game.components.view.objects;

import com.properties.components.Layouts;
import com.tree.TreeBase;
import com.view.ComponentView;
import game.core.objects.GameObject;
import game.core.objects.Scene;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ScenePanel extends Canvas {

    private final Tab tab;

    private final GraphicsContext gc;

    private GameObject gameObject;

    private Scene scenes;
    private static long lastUpdate = 0;

    private final ScrollPane scrollPane;

    private final AnimationTimer animationTimer;
    private static int index = 0;
    private static double[] frameRates = new double[100];
    public ScenePanel() {
        setId("gameScenePanel");
        this.setFocused(false);
        Layouts.alignment(this, Layouts.ALL);
        scrollPane = new ScrollPane(this);
        scrollPane.setFocusTraversable(false);
        tab = TreeBase.newTab("Scene", this);
        gc = getGraphicsContext2D();
        animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                if (lastUpdate > 0)
                {
                    long nanosElapsed = now - lastUpdate;
                    double frameRate = 1000000000.0 / nanosElapsed;
                    index %= frameRates.length;
                    frameRates[index++] = frameRate;
                   // MainViewController.stage.setTitle(String.format("Current frame rate: %.3f fps", getInstantFPS()));
                    drawing();
                }
                lastUpdate = now;
            }
        };
        getAnimationTimer().start();
    }

    public void drawing() {
        gc.clearRect(0,0, getWidth(), getHeight());
        if (getScenes() != null)
            getScenes().render(gc);
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

        System.out.println("gameObject: " + gameObject.getName());
        this.gameObject = gameObject;
    }

    /**
     * Returns the instantaneous FPS for the last frame rendered.
     *
     * @return
     */
    public static double getInstantFPS()
    {
        return frameRates[index % frameRates.length];
    }

    /**
     * Returns the average FPS for the last 100 frames rendered.
     * @return
     */
    public static double getAverageFPS()
    {
        double total = 0.0d;

        for (int i = 0; i < frameRates.length; i++)
        {
            total += frameRates[i];
        }

        return total / frameRates.length;
    }

    public Scene getScenes() {
        return scenes;
    }

    public void setScenes(Scene scenes) {
        this.scenes = scenes;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }
}
