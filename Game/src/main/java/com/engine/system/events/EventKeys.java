package com.engine.system.events;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

public class EventKeys {

    private static boolean keys[] = new boolean[256];
    private static ArrayList<Consumer<Character>> events = new ArrayList<>();

    public static void initEvent(Scene scene){
        scene.setOnKeyPressed(e -> {

            onKeyPressed(e.getCode().getChar().charAt(0));
        });
        scene.setOnKeyReleased(e -> {
            onKeyReleased(e.getCode().getChar().charAt(0));
        });
    }

    public static void onKeyPressed(char c){
        //keys[c] = true;
        events.forEach(e -> e.accept(c));
    }

    public static void addEventKeyPressed(Consumer<Character> event)
    {
        events.add(event);
    }

   public static void onKeyReleased(char c){
       //keys[c] = false;

   }

    public boolean getKey(char k)
    {
        return keys[k];
    }



}
