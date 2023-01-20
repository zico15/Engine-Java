package game.project.build.classBuild;

public class ClassFileJavaEmpty extends CreateClassFile{
    public ClassFileJavaEmpty(String className, String packageName) {
        super(className, packageName == null ? "game.project" : "game.project." + packageName);
        addImport("game.core.objects.*");
        addImport("game.core.components.GameObjectScript");
        addImport("game.opengl.renderer.Graphics2D");
        setExtendsName("GameObjectScript");
        createBock("public", "void", "start", null).add("\n\n");
        newLine();
        createBock("public", "void", "update", null).add("\n\n");
        newLine();
        createBock("public", "void", "render", "Graphics2D graphics2D").add("\n\n");
    }
}
