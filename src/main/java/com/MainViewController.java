package com;

import com.canva.CanvasView;
import com.system.FileSistem;
import com.system.SystemLib;
import com.tree.TreeBase;
import com.tree.TreeResourceController;
import com.tree.TreeSceneController;
import engine2d.components.ComponentBase;
import engine2d.components.Sprite;
import engine2d.system.FileController;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import plugins.Plugins;
import java.io.File;

public class MainViewController extends Control {


    public static CanvasView canvas = new CanvasView();
    private static MainViewController MAIN_VIEW;
    @FXML
    private   ProgressBar progressBar;
    @FXML
    private AnchorPane panelProject;
    @FXML
    private MenuBar menuBarFile;
    @FXML
    private TabPane tabProperties;

    @FXML
    private TabPane tabPaneMain;

    private String name;
    public GraphicsContext gc;
    private static TreeSceneController treeScene;
    public static TreeResourceController treeResource;

    private static AnchorPane  listProperties;

    private static TabPane tab;

    public static TreeSceneController getTreeScene() {
        return treeScene;
    }

    public static void setTreeScene(TreeSceneController treeScene) {
        int index = -1;
        treeScene.setTabView(TreeBase.newTab("Scene", treeScene));
        if (MainViewController.treeScene != null)
             index = getTabProperties().getTabs().indexOf(MainViewController.treeScene.getTabView());
        if (index >= 0)
            getTabProperties().getTabs().set(index, treeScene.getTabView());
        else
            getTabProperties().getTabs().add(treeScene.getTabView());
        MainViewController.treeScene = treeScene;
    }

    public static TreeResourceController getTreeResource() {
        return treeResource;
    }

    public static void setTreeResource(TreeResourceController treeResource) {
        int index = -1;
        treeResource.setTabView(TreeBase.newTab("Resource", treeResource));
        if (MainViewController.treeResource != null)
            index = getTabProperties().getTabs().indexOf(MainViewController.treeResource.getTabView());
        if (index >= 0)
            getTabProperties().getTabs().set(index, treeResource.getTabView());
        else
            getTabProperties().getTabs().add(treeResource.getTabView());
        MainViewController.treeResource = treeResource;
    }

    public static AnchorPane getListProperties() {
        return listProperties;
    }

    public static void setListProperties(AnchorPane listProperties) {
        MainViewController.listProperties = listProperties;
    }

    public static TabPane getTab() {
        return tab;
    }

    public static void setTab(TabPane tab) {
        MainViewController.tab = tab;
    }

    @FXML
    private void initialize() {
        MAIN_VIEW = this;
        setTreeScene(new TreeSceneController());
        setTreeResource(new TreeResourceController());
        setListProperties(new AnchorPane());
        getTabProperties().getTabs().add(TreeBase.newTab("Properties", getListProperties()));
        getTabPaneMain().getTabs().add(TreeBase.newTab("Scene", canvas));
        setTab(getTabPaneMain());
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

    public static ProgressBar getProgressBar() {
        return MAIN_VIEW.progressBar;
    }

    public static AnchorPane getPanelProject() {
        return MAIN_VIEW.panelProject;
    }

    public static MenuBar getMenuBarFile() {
        return MAIN_VIEW.menuBarFile;
    }

    public static TabPane getTabProperties() {
        return MAIN_VIEW.tabProperties;
    }

    public static TabPane getTabPaneMain() {
        return MAIN_VIEW.tabPaneMain;
    }
}