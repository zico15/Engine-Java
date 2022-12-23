package com.project;


import java.io.File;

public class Project {

    private static Project project = new Project();


    private File directory;

    public Project() {

        project = this;
    }

    public Project(File directory) {

        this.setDirectory(directory);
        project = this;
    }

    public static Project getProject() {
        return project;
    }

    public static void setProject(Project project) {
        Project.project = project;
        ;
    }

    public static void save() {

    }

    public static void load() {
        if (Project.getProject() != null) {

        }
    }


    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}
