package game.core.components;

import game.project.build.classBuild.CreateClassFile;

import java.io.File;

public class Script extends ComponentBase {

    private File file;
    private String packageName;


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void addComponentToScript(CreateClassFile blockClass, CreateClassFile.functionBlock block) {
        blockClass.addImport("game.core.components.GameObjectScript");
        String className = getFile().getName();
        className = className.contains(".java") ? className.replace(".java", "").trim() : className.trim();

        block.add(String.format("      %s.addComponent(new %s());",  block.getThisName(), className));
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
