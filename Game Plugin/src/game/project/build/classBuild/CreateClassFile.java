package game.project.build.classBuild;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public  class CreateClassFile   {

    private ArrayList<String> classFile = new ArrayList<>();

    private ArrayList<String> imports = new ArrayList<>();

    private ArrayList<String> implement = new ArrayList<>();

    private ArrayList<functionBlock> functionBlocks = new ArrayList<>();

    private final String className;
    private final String packageName;

    private String extendsName;

    public  CreateClassFile(String className, String packageName)
    {
       this.className = className;
       this.packageName = packageName;
       this.extendsName = null;
    }

    public String head(){
        String h = "package " + packageName + ";\n\n";

        for (String importName : imports)
            h += "import " + importName + ";\n";
        h += "\npublic class " + className + (extendsName != null ? (" extends " + extendsName) : "") + " ";
        for (int i = 0; i < implement.size(); i++) {
            h += implement.get(i) + ((i + 1) < implement.size() ? "," : " ");
        }
        h += "  {\n\n\n";
        return h;
    }

    public void addImport(String importName){
        imports.add(importName);
    }

    public void addImplement(String implementName){
        this.implement.add(implementName);
    }
    public void newLine(){
        classFile.add("\n");
    }

    public functionBlock createBock(String type, String returnType, String name, String parameters){
        functionBlock block = new functionBlock(type, returnType, name, parameters);
        functionBlocks.add(block);
        return block;
    }
    public void newLine(int n){
        for (int i = 0; i < n; i++)
            newLine();
    }
    public void add(String line){
        classFile.add(line);
    }

    public void save(@NotNull File file)
    {
        classFile.add(0, head());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            try {
                BufferedWriter bw = new BufferedWriter(new PrintWriter(file));
                try {
                    for (String line : classFile)
                        bw.write(line);
                    for (functionBlock block : functionBlocks)
                        bw.write(block.toString());
                    bw.write("\n}");
                } catch (Throwable var6) {
                    try {
                        bw.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                    throw var6;
                }
                bw.close();
            } catch (IOException var7) {
                var7.printStackTrace();
            }
        } catch (IOException var8) {
            throw new RuntimeException(var8);
        }
    }

    public void setExtendsName(String extendsName) {
        this.extendsName = extendsName;
    }

    public class functionBlock {

        private ArrayList<String> lines = new ArrayList<>();


        public functionBlock(String type, String returnType, String name, String parameters){
            if (returnType == null)
                returnType = "";
            lines.add("     " + type + " " + returnType + " " + name +
                    (parameters == null ? "()" : "( " + parameters +" )") +" {");
        }

        public void add(String line){
            lines.add("         "+line);
        }

        @Override
        public String toString() {
            String text = "";

            for (String line : lines)
                text += line + "\n";
            text += "     }\n";
            return text;
        }
    }
}
