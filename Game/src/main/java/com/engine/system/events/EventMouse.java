package com.engine.system.events;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.function.Consumer;

public class EventMouse {
    private static ArrayList<Consumer<MouseEvent>> mouseClicked = new ArrayList<>();
    private static ArrayList<Consumer<MouseEvent>> mouseMoved = new ArrayList<>();

    private static ArrayList<Consumer<MouseEvent>> mousePressed = new ArrayList<>();


    public static void initEvent(Scene scene){
        scene.setOnMouseClicked(e -> onMouseClicked(e));
        scene.setOnMouseMoved(e -> onMouseMoved(e));
        scene.setOnMousePressed(e -> onMousePressed(e));
    }

    public static void onMouseClicked(MouseEvent e){

        mouseClicked.forEach(evt -> evt.accept(e));
    }

    public static void onMouseMoved(MouseEvent e){
        mouseMoved.forEach(evt -> evt.accept(e));

    }

    public static void onMousePressed(MouseEvent e){
        mousePressed.forEach(evt -> evt.accept(e));

    }

    public static void addEventMouseClicked(Consumer<MouseEvent> event)
    {
        mouseClicked.add(event);
    }

    public static void addEventMouseMoved(Consumer<MouseEvent> event)
    {
        mouseMoved.add(event);
    }

    public static void addEventMousePressed(Consumer<MouseEvent> event)
    {
        mousePressed.add(event);
    }


}
