package com.project;

import engine2d.objects.GameProject;
import engine2d.objects.Scene;
import engine2d.system.FileController;

import java.io.File;

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
            FileController.save(new File(Project.getProject().getDirectory(), "settings.cx"), Project.getProject().gameProject);
        }
    }

    public static void load(){
        if (Project.getProject() != null){
             Project.getProject().gameProject = GameProject.load(new File(Project.getProject().getDirectory(), "settings.cx"));
             if (Project.getProject().gameProject  != null) {

             }
        }
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
