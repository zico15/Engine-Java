package com.tree;

import com.MainViewController;
import com.list.PropertiesBase;
import com.system.ImageBase;
import engine2d.objects.ObjectBase;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class TreeItemObject extends TreeItemBase {


    public ObjectBase ob;

    private TextArea textArea;

    public TreeItemObject(ObjectBase ob) {
        this.ob = ob;
        this.type = ob.type;
        super.setValue(ob.name);
        setGraphic(new ImageView(ImageBase.getIcons(getExtension(type))));
    }

    @Override
    public void preview() {
        MainViewController.listProperties.getChildren().clear();
        if ("tilemap".equals(type))
            previewTilemap();
    }

    private void previewTilemap() {

        if (panel == null)
            panel = PropertiesBase.tileMap(this);
        MainViewController.listProperties.getChildren().add(panel);
    }

    @Override
    public boolean delete() {
        return (true);
    }


}
