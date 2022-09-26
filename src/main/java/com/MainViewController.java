package com;

import com.canva.CanvasView;
import com.project.Project;
import com.system.FileSistem;
import com.tree.TreeBase;
import com.tree.TreeResourceController;
import com.tree.TreeSceneController;
import engine2d.components.ComponentBase;
import engine2d.components.Sprite;
import engine2d.objects.Scene;
import engine2d.system.FileController;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class MainViewController extends Control {

    public static CanvasView canvas = new CanvasView();
    @FXML
    private AnchorPane panelProject;

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
        treeScene = new TreeSceneController();
        treeResource = new TreeResourceController();
        listProperties = new AnchorPane();
        tabProperties.getTabs().add(TreeBase.newTab("Scene", treeScene));
        tabProperties.getTabs().add(TreeBase.newTab("Resource", treeResource));
        tabProperties.getTabs().add(TreeBase.newTab("Properties", listProperties));
        tabPaneMain.getTabs().add(TreeBase.newTab("Scene", canvas));
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
        Project.getProject().gameProject.getScenes().add(scene);
        Project.getProject().gameProject.setScene(scene);
        treeScene.load(scene);
        System.out.println("newProject: " + file);
    }
    @FXML
    protected void openProject()
    {
        File file = FileSistem.openFolder();
        if (file == null || !file.isDirectory())
            return;
       // PROJECT = new Project(file);
        treeResource.clear();
        treeResource.setRootFile(file);
        treeResource.load(file, treeResource.getRootItem());
        System.out.println(file);
    }

    @FXML
    protected void saveProject()
    {
        File file = FileSistem.saveFile();
       // FileController.save(file, PROJECT.gameProject);
        System.out.println(file);
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