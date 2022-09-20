package com;

import com.canva.CanvasView;
import com.project.Project;
import com.system.FileSistem;
import com.tree.TreeBase;
import com.tree.TreeResourceController;
import com.tree.TreeSceneController;
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
    public static Project PROJECT = new Project();

    public static TabPane tab;
    @FXML
    private void initialize() {
        tab = tabPaneMain;
        System.out.println("initialize");
        treeScene = new TreeSceneController();
        treeResource = new TreeResourceController();
        listProperties = new AnchorPane();
        tabProperties.getTabs().add(TreeBase.newTab("Scene", treeScene));
        tabProperties.getTabs().add(TreeBase.newTab("Resource", treeResource));
        tabProperties.getTabs().add(TreeBase.newTab("Properties", listProperties));
        //PropertiesBase.alignment(canva, 0,0,0,0);
        tabPaneMain.getTabs().add(TreeBase.newTab("Scene", canvas));
        canvas.scene = PROJECT.scene;
    }

    @FXML
    protected void newProject()
    {
        File file = FileSistem.saveFile();
        if (file == null)
            return;
        file.mkdir();
        treeResource.clear();
        treeResource.setRootFile(file);
        PROJECT = new Project(file);

        System.out.println("newProject: " + file);
    }
    @FXML
    protected void openProject()
    {
        File file = FileSistem.openFolder();
        if (file == null || !file.isDirectory())
            return;
        PROJECT = new Project(file);
        treeResource.clear();
        treeResource.setRootFile(file);
        treeResource.load(file, treeResource.getRootItem());
        System.out.println(file);
    }

    @FXML
    protected void saveProject()
    {
        File file = FileSistem.saveFile();
        System.out.println(file);
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