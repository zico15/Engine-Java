package com.build;

import com.project.Project;
import engine2d.objects.GameNode;
import engine2d.system.FileController;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.io.IOException;

public class Build extends Thread{

    private final Project project;
    private ProgressBar   progressBar;
    public Build(Project project, ProgressBar   progressBar)
    {
        this.project = project;
        this.progressBar = progressBar != null ? progressBar : new ProgressBar();
        if (project == null)
            return;
        System.out.println("size: " + project.getSizeElements());
    }
    
    private void create(final File file, GameNode node){
        if (file == null || !file.exists() || node == null)
            return;
        File f = new File(file, node.name + ".java");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        node.getChildren().forEach(c -> create(file, node));
    }

    @Override
    public void run() {
        progressBar.setVisible(true);
        File build = new File(project.getDirectory(), "Build");
        if (!build.exists())
            build.mkdirs();
        project.gameProject.getPrefabs().forEach(c -> create(build, c.getGameObject()));
        while (progressBar.getProgress() < 1){
            try {
                sleep(1000);
                progressBar.setProgress(progressBar.getProgress() + 0.05);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        progressBar.setVisible(false);
    }

}
