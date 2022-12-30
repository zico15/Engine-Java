package game.core.system;

import game.components.tree.base.fileType;
import game.components.tree.objects.SceneComponentTree;
import javafx.scene.image.Image;

import java.io.IOException;

public final class Icons {

    private static final  Image[] ICONS;

    static {
        try {
            ICONS = new Image[]{
                    new Image(SceneComponentTree.class.getResource("/resources/icons/file.png").openStream()),
                    new Image(SceneComponentTree.class.getResource("/resources/icons/folder_yellow.png").openStream()),
                    new Image(SceneComponentTree.class.getResource("/resources/icons/folder.png").openStream()),
                    new Image(SceneComponentTree.class.getResource("/resources/icons/folder_read.png").openStream()),
                    new Image(SceneComponentTree.class.getResource("/resources/icons/file_img.png").openStream()),
                    new Image(SceneComponentTree.class.getResource("/resources/icons/java.png").openStream()),
                    new Image(SceneComponentTree.class.getResource("/resources/icons/file_code.png").openStream()),
                    new Image(SceneComponentTree.class.getResource("/resources/icons/scene.png").openStream()),
                    new Image(SceneComponentTree.class.getResource("/resources/icons/object.png").openStream()),
                    new Image(SceneComponentTree.class.getResource("/resources/icons/object.png").openStream()),
                    new Image(SceneComponentTree.class.getResource("/resources/icons/tilemap.png").openStream())
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Image get(fileType type){
        return ICONS[type.ordinal()];
    }
}
