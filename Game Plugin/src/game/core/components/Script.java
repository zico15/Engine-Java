package game.core.components;

import game.core.system.FileSystemGame;
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
        if (file != null)
            packageName =  "game.project." + FileSystemGame.getPackage(file);
        System.out.println("Script: " + packageName);
        System.out.printf("");
    }

    @Override
    public void addComponentToScript(CreateClassFile blockClass, CreateClassFile.functionBlock block) {
        if (packageName != null && !packageName.trim().isEmpty()){
            blockClass.addImport(getPackageName());
            String className = getFile().getName();
            className = className.contains(".java") ? className.replace(".java", "").trim() : className.trim();
            block.add(String.format("      %s.addComponent(new %s());",  block.getThisName(), className));
        }
    }

    public String getPackageName() {
        return packageName;
    }

}
