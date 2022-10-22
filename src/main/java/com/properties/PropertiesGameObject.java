package com.properties;

import com.properties.components.ImageProperties;
import com.properties.components.MenuComponents;
import com.properties.components.ScriptProperties;
import com.tree.TreeItemObject;
import engine2d.components.Script;
import engine2d.components.Sprite;
import engine2d.objects.GameObject;
import javafx.scene.Node;

import static com.properties.PropertiesItem.*;
public class PropertiesGameObject extends PropertiesBase {

    public PropertiesGameObject(TreeItemObject item){
        super(item);
    }

    @Override
    public void createProperties() {
        System.out.println("PropertiesGameObject");
        setFocusTraversable(false);
        getChildren().add(itemTitle(getItem(),20));
        getChildren().add(itemName(getItem(),25));
        getChildren().add(itemPosition(getItem(),20));
        loadComponents(getGameObject());
        getChildren().add(new MenuComponents(this));
    }

    private void loadComponents(GameObject gameObject){
        gameObject.getComponents().forEach(c-> {
            if (c instanceof Sprite)
                getChildren().add(new ImageProperties((Sprite) c, this));
            else if (c instanceof Script)
                getChildren().add(new ScriptProperties((Script) c, this));
        });
    }

}
