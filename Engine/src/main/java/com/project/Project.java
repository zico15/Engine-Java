package com.project;


import java.io.File;
import java.io.Serializable;

public class Project implements Serializable {

    private static Project project = new Project();
    private File directory;

    private File assets;

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

    public void save() {

    }

    public void load(File file) {
        if (Project.getProject() != null) {

        }
    }


    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.assets = new File(directory, "assets");
        if (!this.assets.exists())
            assets.mkdirs();
        this.directory = directory;
    }

    public File getAssets() {
        return assets;
    }
}
