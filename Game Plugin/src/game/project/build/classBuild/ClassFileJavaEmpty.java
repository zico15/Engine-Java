package game.project.build.classBuild;

public class ClassFileJavaEmpty extends CreateClassFile{
    public ClassFileJavaEmpty(String className, String packageName) {
        super(className, packageName);
        addImport("game.core.objects.*");
        setExtendsName("GameObjectScript");
        createBock("public", "void", "start", null).add("\n\n");
        newLine();
        createBock("public", "void", "update", null).add("\n\n");
        newLine();
        createBock("public", "void", "render", null).add("\n\n");
    }
}
