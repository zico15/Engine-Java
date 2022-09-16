package com.tree;

import com.system.ImageBase;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;

public class TreeItemBase extends TreeItem {

    public String     type;
    public Tab       tab;
    public Parent    panel = null;

    public void preview() {
    }

    public boolean delete() {
        return (true);
    }

    public String getExtension(String type) {
        if ("folder".equals(type))
            return (ImageBase.ICON_FOLDER);
        if ("java".equals(type))
            return (ImageBase.ICON_JAVA);
        if ("scene".equals(type))
            return (ImageBase.ICON_SCENE);
        if ("object".equals(type))
            return (ImageBase.ICON_OBJECT);
        if ("tilemap".equals(type))
            return (ImageBase.ICON_TILEMAP);
        return (ImageBase.ICON_FILE);
    }
}
