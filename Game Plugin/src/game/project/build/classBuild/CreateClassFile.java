package game.project.build.classBuild;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public  class CreateClassFile   {

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
        String h = packageName != null ? "package " + packageName + ";\n\n" : "";

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
        functionBlocks.add(new functionBlock("\n"));
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
        functionBlocks.add(new functionBlock(line));
    }

    /***
     * String %s
     * Float %f
     * Char  %c
     * Int   %i
     * ***/
    public void add(String format, Object... args){
        add(String.format(format, args));
    }

    public void save(@NotNull File file)
    {
        functionBlocks.add(0, new functionBlock(head()));
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            try {
                BufferedWriter bw = new BufferedWriter(new PrintWriter(file));
                try {
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

    public static class functionBlock {

        private ArrayList<String> lines = new ArrayList<>();
        private boolean isLine;


        public functionBlock(String type, String returnType, String name, String parameters){
            isLine = false;
            if (returnType == null)
                returnType = "";
            lines.add("     " + type + " " + returnType + " " + name +
                    (parameters == null ? "()" : "( " + parameters +" )") +" {");
        }

        public functionBlock(String line)
        {
            isLine = true;
            lines.add(line);
        }

        public void add(String line){
            lines.add("         "+line);
        }

        /***
         * String %s
         * Float %f
         * Char  %c
         * Int   %i
         * ***/
        public void add(String format, Object... args){
            add(String.format(format, args));
        }
        @Override
        public String toString() {
            String text = "";

            for (String line : lines)
                text += line + "\n";
            if (!isLine)
                text += "     }\n";
            return text;
        }
    }
}
