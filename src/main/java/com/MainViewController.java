package com;

import com.canva.CanvaView;
import com.project.Project;
import com.system.FileSistem;
import com.tree.TreeBase;
import com.tree.TreeResourceController;
import com.tree.TreeSceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;

public class MainViewController extends Control {

    CanvaView canva = new CanvaView();
    @FXML
    private AnchorPane panelProject;

    @FXML
    private TabPane tabProperties;

    @FXML
    private TabPane tabPaneMain;
    private GraphicsContext gc;
    private TreeSceneController treeScene;
    private TreeResourceController treeResource;
    private Project project;
    @FXML
    private void initialize() {
        System.out.println("initialize");
        treeScene = new TreeSceneController();
        treeResource = new TreeResourceController();
        tabProperties.getTabs().add(TreeBase.newTab("Scene", treeScene));
        tabProperties.getTabs().add(TreeBase.newTab("Resource", treeResource));
        tabPaneMain.getTabs().add(TreeBase.newTab("Scene", canva));
    }

    @FXML
    protected void newProject()
    {
        File file = FileSistem.saveFile();
        if (!file.exists())
            file.mkdir();
        treeResource.clear();
        treeResource.setRootFile(file);
        project = new Project(file);

        System.out.println("newProject: " + file);
    }
    @FXML
    protected void openProject()
    {
        File file = FileSistem.openFolder();
        if (!file.isDirectory())
            return;
        project = new Project(file);
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

        canva.drawLines();
    }
}