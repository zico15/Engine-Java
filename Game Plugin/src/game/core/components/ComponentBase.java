package game.core.components;


import game.core.base.BaseStructure;
import game.core.objects.ObjectBase;
import game.project.build.classBuild.CreateClassFile;
import javafx.scene.canvas.GraphicsContext;

public abstract class ComponentBase implements BaseStructure {

    private ObjectBase parent;

    @Override
    public void start() {

    }

    @Override
    public void render(GraphicsContext graphics2D) {

    }


    @Override
    public void update() {

    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    public final ObjectBase getParent() {
        return parent;
    }

    public final void setParent(ObjectBase parent) {
        this.parent = parent;
    }

    public void load_system() {

    }

    public abstract void addComponentToScript(CreateClassFile blockClass, CreateClassFile.functionBlock block);
}
