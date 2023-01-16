package game.components.properties;

import com.properties.components.Layouts;
import game.components.view.objects.GameObjectProperties;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public abstract class ComponentProperties <T> {

    protected final GameObjectProperties properties = GameObjectProperties.properties;
    private  final T view;

    protected ComponentProperties( T view) {
        this.view = view;
        init();
    }

    public abstract void init();


    public final T getView(){
        return view;
    }


    public static AnchorPane createPropertiesMenu(){
        MenuButton menu = new MenuButton("Components");
        menu.setAlignment(Pos.CENTER);
        menu.setPopupSide(Side.RIGHT);
        menu.getStyleClass().add("my-menuButton");
        menu.getItems().add(ImageProperties.creatingProperties(GameObjectProperties.properties));
        menu.getItems().add(ScriptProperties.creatingProperties(GameObjectProperties.properties));
        Layouts.alignment(menu, Layouts.ALL);
        return new AnchorPane(menu);
    }
}
