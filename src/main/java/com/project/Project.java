package com.project;

import java.io.File;

public class Project {

    public static Project project;
    private File directory;
    public Project(){}

    public Project(File directory){
       this.directory = directory;
    }
}
