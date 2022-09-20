package com.tree;

import com.MainViewController;
import com.properties.PropertiesGameObject;
import com.properties.PropertiesScene;
import com.properties.PropertiesTileMaps;
import com.system.ImageBase;
import engine2d.objects.GameObject;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class TreeItemObject extends TreeItemBase {


    public GameObject ob;

    private TextArea textArea;

    public TreeItemObject(GameObject ob) {
        this.ob = ob;
        this.type = ob.type;
        super.setValue(ob.name);
        setGraphic(new ImageView(ImageBase.getIcons(getExtension(type))));
    }

    @Override
    public void preview() {
        MainViewController.listProperties.getChildren().clear();
        if (properties != null){
            MainViewController.listProperties.getChildren().add(properties.getProperties());
            MainViewController.canva.setOnMouseMoved(e -> onMouseMove(e));
            MainViewController.canva.setOnMouseClicked(e -> onMouseClick(e));
            return;
        }
        System.out.println("type: " + type);
        if ("Scene".equals(type))
            properties = new PropertiesScene(this);
        else if ("TileMaps".equals(type))
            properties = new PropertiesTileMaps(this);
        else
            properties = new PropertiesGameObject(this);
        MainViewController.listProperties.getChildren().add(properties.getProperties());
        MainViewController.canva.setOnMouseMoved(e -> onMouseMove(e));
        MainViewController.canva.setOnMouseClicked(e -> onMouseClick(e));
    }

    @Override
    public boolean delete() {
        return (true);
    }


}
