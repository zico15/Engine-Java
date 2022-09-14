package com.tree;

import com.system.ImageBase;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

import java.io.File;

public class TreeItemResource extends TreeItem   {

    public String   type;
    public File     file;
    public TreeItemResource(File file){
        setFile(file);
    }
    public void select()
    {

    }

    public boolean delete(){
        if (file.exists())
            file.delete();
        return (true);
    }

    public void setFile(File file){
        this.file = file;
        type = file.getName();
        if (type.contains("."))
        {
            type = type.substring(type.lastIndexOf(".") + 1, type.length());
            System.out.println("type: "+type);
        }else
            type = "file";
        super.setValue(file.getName());
        setGraphic(new ImageView(ImageBase.getIcons(getExtension(file))));
    }
    private String getExtension(File file){
        if (file.exists() && file.isDirectory())
            return (ImageBase.ICON_FOLDER);
        if ("java".equals(type))
            return (ImageBase.ICON_JAVA);
        if ("scene".equals(type))
            return (ImageBase.ICON_SCENE);
        return (ImageBase.ICON_FILE);
    }
}
