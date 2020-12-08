/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.base;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 35191
 */
public class Resoucer {
    
   
    private static ArrayList<File> RESOUCER;
    
    public static void load(){
 
      //  All Files	C:\Users\35191\Documents\NetBeansProjects\SpaceEngine\src\engine\system
        
       
        String path = System.getProperty("user.dir");
       RESOUCER = new ArrayList<>();
       
        FileWriter arq;
        try {
            arq = new FileWriter(path+"\\src\\engine\\system\\R.java");
            PrintWriter gravar = new PrintWriter(arq);    
            gravar.println("package engine.system;");
            gravar.println("");
            gravar.println("public class R {");
            gravar.println("");
            gravar.println("public static R PROJECT = new R();");
            gravar.println("");
            if(dirAll(new File(path+"\\src\\resoucer"),null,gravar) != null){
                     gravar.println("}");                      
            } 
            
   
            gravar.println("}");
            arq.close();
          
            
        } catch (IOException ex) {
            Logger.getLogger(Resoucer.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
     RESOUCER = null;
    }
    
  
 
    
//     public static TreeItem getTreeItem(File file){
//
//        int index  = file.getName().lastIndexOf(".");
//         
//        String tipo = index >= 0 ? file.getName().substring(index + 1, file.getName().length()) : "pasta";
//        //  System.out.println("tipo: "+tipo);
//        TreeItem item = new TreeItem(file.getName(), "/resoucer/icons/file.png");
//        item.setFile(file);
//        switch(tipo){
//        
//            case "scn":
//                item.setIcon("/resoucer/icons/iconScene.png");
//                break;   
//            case "mp3":
//                item.setIcon("/resoucer/icons/audio.png");
//                break;
//            case "ogg":
//                item.setIcon("/resoucer/icons/audio.png");
//                break;
//            case "wma":
//                item.setIcon("/resoucer/icons/audio.png");
//                break;
//            case "alac":
//                item.setIcon("/resoucer/icons/audio.png");
//                break;
//            case "aiff":
//                item.setIcon("/resoucer/icons/audio.png");
//            case "wav":
//                item.setIcon("/resoucer/icons/audio.png");
//                break;
//            case "pasta":                
//                item.setIcon("/resoucer/icons/pasta2.png");
//                break;
//            case "png":
//                item.setResoucer(false);
//                item.setIcon(file.getAbsolutePath());
//                break;
//            case "jpng":
//                item.setResoucer(false);
//                item.setIcon(file.getAbsolutePath());
//                 break;
//            case "jpeg":
//                item.setResoucer(false);
//                item.setIcon(file.getAbsolutePath());
//                 break;
//            case "gif":
//                item.setResoucer(false);
//                item.setIcon(file.getAbsolutePath());
//                break;
//             case "bmp":
//                 item.setResoucer(false);
//                 item.setIcon(file.getAbsolutePath());
//             break;
//               case "tiff":
//                item.setResoucer(false);
//                item.setIcon(file.getAbsolutePath());
//                break;
//            case "psd":
//                item.setResoucer(false);
//                item.setIcon(file.getAbsolutePath());
//                break;
//            case "webp":
//                item.setResoucer(false);
//                item.setIcon(file.getAbsolutePath());
//                break;
//            case "java":
//            
//                item.setIcon("/resoucer/icons/scriptJava3.png");
//                break;
//            case "html":
//            
//                item.setIcon("/resoucer/icons/iconhtml.png");
//                break;
//                
//        }
//     
//
//     return item;
//     
//     }
     
     private static File dirAll(File file,File pastaAtual,PrintWriter gravar){
        
        if(file!=null && file.listFiles()!=null){
            
            for(File pasta : file.listFiles()){
                if(pasta!=null ){
               
                    if(pasta.isDirectory()){
                         
                        
                        if(pasta != pastaAtual){
                            
                            
                            
                          
                            File finalPsta = pastaAtual != null ? pastaAtual : file;
                            
                            if(!RESOUCER.contains(finalPsta)){
                                for(File subSasta : finalPsta.listFiles()){                        
                                     if(subSasta.isDirectory()){
                                        gravar.println(" public "+subSasta.getName()+ " "+subSasta.getName().toUpperCase()+ " = new "+subSasta.getName()+"();");
                                     }
                                }                                
                                RESOUCER.add(file);
                            }
                           if(pastaAtual!= null){
                             gravar.println("}");
                            }
                            pastaAtual = pasta;
                            gravar.println("");
                            
                            gravar.println("public class "+pasta.getName()+" {");
                        }
                    
                    }else{
                    String key = pasta.getName().toUpperCase();
                    key = key.replace("!", "");
                    key = key.replace("?", "");
                    key = key.replace("@", "");
                    key = key.replace("-", "_");
                    key = key.replace("$", "");
                    key = key.trim();
                    if(key.contains(".")){
                        key = key.substring(0,key.lastIndexOf("."));
                    }
                    
                    String url = pasta.getAbsolutePath();
                    //System.out.println(url);
                    if(url.contains("\\")){
                    url = pasta.getAbsolutePath().replace('\\', '/');
                    }
                   gravar.println("  public String "+key+"= "+'"'+url+'"'+";");
                    }
                   //RESOUCER.put(key, url);}
      
                dirAll(pasta,pastaAtual,gravar);
                }
            
        } 
        }
       
        return pastaAtual;
    }
    
     public static boolean execRuntime(String file){
        try {
            Runtime.getRuntime().exec(file);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Resoucer.class.getName()).log(Level.SEVERE, null, ex);
        }
               return false;
     }
   
}
