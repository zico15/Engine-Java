package com.tree;

import com.properties.PropertiesBase;
import com.system.ImageBase;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;

public class TreeItemBase extends TreeItem {

    public String     type;
    public Tab       tab;
    public PropertiesBase properties = null;

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
        if ("Scene".equals(type))
            return (ImageBase.ICON_SCENE);
        if ("GameObject".equals(type))
            return (ImageBase.ICON_OBJECT);
        if ("TileMaps".equals(type))
            return (ImageBase.ICON_TILEMAP);
        return (ImageBase.ICON_FILE);
    }
}
