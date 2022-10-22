package com.properties;

import com.tree.TreeItemObject;
import engine2d.objects.GameObject;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import static com.properties.PropertiesItem.NONE;

public abstract class PropertiesBase extends VBox{

    private final TreeItemObject item;
    private final GameObject gameObject;

    PropertiesBase(TreeItemObject itemObject){
        this.item = itemObject;
        this.gameObject = itemObject.ob;
        PropertiesItem.alignment(this, NONE, NONE, 0, 0);
        createProperties();
    }

    public abstract void createProperties();

    public void onMouseMove(MouseEvent e){

    }

    public void onMouseClick(MouseEvent e){

    }

    public TreeItemObject getItem() {
        return item;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
    public final int getSize(){
        return getChildren().size();
    }
}
