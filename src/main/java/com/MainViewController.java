package com;

import com.build.Build;
import com.canva.CanvasView;
import com.project.Project;
import com.system.FileSistem;
import com.system.SystemLib;
import com.tree.TreeBase;
import com.tree.TreeResourceController;
import com.tree.TreeSceneController;
import engine2d.components.ComponentBase;
import engine2d.components.Sprite;
import engine2d.objects.Scene;
import engine2d.project.Prefab;
import engine2d.system.FileController;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import plugins.Plugins;

import javax.sound.sampled.Port;
import java.io.File;

public class MainViewController extends Control {


    public static CanvasView canvas = new CanvasView();
    public static MainViewController mainViewController;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private AnchorPane panelProject;
    @FXML
    public MenuBar menuBarFile;
    @FXML
    private TabPane tabProperties;

    @FXML
    private TabPane tabPaneMain;
    private GraphicsContext gc;
    public static TreeSceneController treeScene;
    public static TreeResourceController treeResource;

    public static AnchorPane  listProperties;

    public static TabPane tab;
    @FXML
    private void initialize() {
        mainViewController = this;
        treeScene = new TreeSceneController();
        treeResource = new TreeResourceController();
        listProperties = new AnchorPane();
        tabProperties.getTabs().add(TreeBase.newTab("Scene", treeScene));
        tabProperties.getTabs().add(TreeBase.newTab("Resource", treeResource));
        tabProperties.getTabs().add(TreeBase.newTab("Properties", listProperties));
        tabPaneMain.getTabs().add(TreeBase.newTab("Scene", canvas));
        tab = tabPaneMain;
    }

    @FXML
    protected void newProject()
    {
        File file = FileSistem.saveFile();
        if (file == null)
            return;
        file.mkdir();
        tab = tabPaneMain;
        treeResource.clear();
        treeResource.setRootFile(file);
        Project.setProject(new Project(file));
        Scene scene = new Scene();
        Prefab sceneMain = new Prefab("Scene", scene);
        Project.getProject().gameProject.setScene(scene);
        Project.getProject().gameProject.setSceneMain(sceneMain);
        Project.getProject().gameProject.addPrefab(sceneMain);
        saveProject();
        treeScene.load(null, scene);
        System.out.println("newProject: " + file);
    }
    @FXML
    protected void openProject()
    {
        File file = FileSistem.openFolder();
        if (file == null || !file.isDirectory())
            return;
        Project.setProject(new Project(file));
        Project.load();
        treeScene.load(null, Project.getScene());
        treeResource.setRootFile(file);
        treeResource.clear();
        treeResource.load(file, treeResource.getRootItem());
        System.out.println(file);
    }

    @FXML
    protected void saveProject()
    {
        Project.save();
        treeResource.clear();
        treeResource.load(Project.getProject().getDirectory(), treeResource.getRootItem());
    }

    @FXML
    protected void buildProject()
    {
        Build build = new Build(Project.getProject(), progressBar);
        build.start();
        System.out.println("Build Project");
    }

    @FXML
    protected void installPlugins(){

        File file = FileSistem.openFile(new FileChooser.ExtensionFilter("JAVA files (*.jar)", "*.jar"));
        if (file == null)
            return;

        Plugins plugins = SystemLib.installPlugins(file);
                plugins.install();
                System.out.println("Plugin: " + plugins != null);


    }

    @FXML
    protected void uninstallPlugins(){

    }

    @FXML
    protected void laodtester() {
        System.out.println("laod");
        File file = FileSistem.openFile();
        engine2d.objects.Scene scene = (engine2d.objects.Scene) FileController.read(file);
        if (scene == null)
            return;
        scene.getChildren().forEach(children -> {
            System.out.println("type: " + children.getType() + " components: " + children.getComponents().size());
            ComponentBase componentBase = children.getComponent("Sprite");
            if (componentBase != null) {
                Sprite sprite = (Sprite) componentBase;
                System.out.println("type: " + sprite.getType() + " image: " + (sprite != null));
            }
        });

    }
    @FXML
    protected void onHelloButtonClick() {
        if (canvas.isRun)
            canvas.loop.stop();
        else
            canvas.loop.start();
        canvas.isRun = !canvas.isRun;
        canvas.render();
    }
}