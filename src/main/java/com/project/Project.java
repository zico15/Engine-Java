package com.project;

import engine2d.objects.Scene;
import engine2d.system.GameProject;

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
