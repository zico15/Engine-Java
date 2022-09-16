package com.project;

import engine2d.objects.Scene;

import java.io.File;

public class Project {

    public static Project project;

    public Scene scene;
    private File directory;
    public Project(){}

    public Project(File directory){
       this.setDirectory(directory);
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}
