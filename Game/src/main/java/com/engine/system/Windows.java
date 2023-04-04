package com.engine.system;

import javafx.stage.Stage;

public class Windows {

    public  static Stage stage;


    public static int getWidth(){
        return (int) stage.getWidth();
    }
    public static int getHeight(){
        return (int) stage.getHeight();
    }
}
