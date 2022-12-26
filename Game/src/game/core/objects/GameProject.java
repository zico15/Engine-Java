package game.core.objects;


import game.core.base.BaseStructure;
import game.core.project.Prefab;
import game.core.system.FileController;
import javafx.scene.canvas.GraphicsContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class GameProject implements BaseStructure {

    private final List<Prefab> prefabs = new ArrayList<>();
    private Prefab sceneMain;
    private Scene scene;

    public static GameProject load(File file) {
        GameProject project = null;
        if (file != null && file.exists() && file.isFile()) {
            project = (GameProject) FileController.read(file);
            System.out.println("load: GameProject");
        }
        return project;
    }

    public static void save(File file, GameProject project) {
        if (file == null || project == null)
            return;
        if (!file.exists())
            file.mkdirs();
        project.getPrefabs().forEach(p -> {
            p.save(file);
        });
        FileController.save(new File(file, "settings.cx"), project);
    }

    @Override
    public void start() {

    }

    @Override
    public void render(GraphicsContext graphics2D) {

    }

    @Override
    public void update() {

    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }


    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        if (scene != null)
            scene.load_system();
    }

    public Prefab getSceneMain() {
        return sceneMain;
    }

    public void setSceneMain(Prefab sceneMain) {
        this.sceneMain = sceneMain;
    }

    public void addPrefab(Prefab prefab) {
        if (!getPrefabs().contains(prefab))
            getPrefabs().add(prefab);
    }

    public List<Prefab> getPrefabs() {
        return prefabs;
    }
}
