package com.build;

import com.project.Project;
import javafx.scene.control.ProgressBar;
import java.io.File;


public class Build extends Thread {

    private final Project project;
    private ProgressBar progressBar;

    public Build(Project project, ProgressBar progressBar) {
        this.project = project;
        this.progressBar = progressBar != null ? progressBar : new ProgressBar();
        if (project == null)
            return;

    }


    @Override
    public void run() {
        progressBar.setVisible(true);
        File build = new File(project.getDirectory(), "Build");
        if (!build.exists())
            build.mkdirs();

        progressBar.setVisible(false);
        System.out.println(Project.getProject().getDirectory());
    }

}
