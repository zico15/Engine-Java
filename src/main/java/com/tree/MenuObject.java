package com.tree;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class MenuObject {

    public static ContextMenu creadMenu() {
        MenuItem addobject = new MenuItem("create object");
        addobject.setOnAction(e -> {
            System.out.println("addobject");
        });
        MenuItem delete = new MenuItem("delete");
        delete.setOnAction(e -> {
            System.out.println("delete");
        });
        return new ContextMenu(addobject, delete);
    }
}
