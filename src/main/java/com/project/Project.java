package com.project;

import engine2d.objects.GameNode;
import engine2d.objects.GameProject;
import engine2d.objects.Scene;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class Project {

    private static Project project = new Project();

    public GameProject gameProject;
    private File directory;

    public Project() {
        gameProject = new GameProject();
        project = this;
    }

    public Project(File directory) {
        gameProject = new GameProject();
        this.setDirectory(directory);
        project = this;
    }

    public static Project getProject() {
        return project;
    }

    public static void setProject(Project project) {
        Project.project = project;;
    }

    public static void save(){
        if (Project.getProject() != null && Project.getProject().gameProject != null){
            GameProject.save(Project.getProject().getDirectory(), Project.getProject().gameProject);
        }
    }

    public static void load(){
        if (Project.getProject() != null){
             Project.getProject().gameProject = GameProject.load(new File(Project.getProject().getDirectory(), "settings.cx"));
             if (Project.getProject().gameProject  != null) {

             }
        }
    }

    private int getSizeGameObject(GameNode ob){
        if (ob == null)
            return 0;
        AtomicInteger size = new AtomicInteger(1);
        size.addAndGet(ob.getComponents().size());;
        ob.getChildren().forEach(c -> {
            size.addAndGet(getSizeGameObject(c));
        });
        return size.get();
    }
    public int getSizeElements(){
        AtomicInteger size = new AtomicInteger(1);
        if (gameProject == null)
            return 0;
        size.addAndGet(gameProject.getPrefabs().size());
        gameProject.getPrefabs().forEach(c -> {
            size.addAndGet(getSizeGameObject(c.getGameObject()));
        });
        return size.get();
    }

    public static Scene getScene(){
        return project.gameProject.getScene();
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}
