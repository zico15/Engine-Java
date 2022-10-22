package com.system;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import plugins.Plugins;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author 35191
 */
public class SystemLib {
    
    /*
    private static final ArrayList<Lib> libs = new ArrayList<>();
    
    
    public static void load(){
        
        String path = System.getProperty("user.dir");
        File files = new File(new File(path),"lib");
        if(files.exists()){
            for(File file : files.listFiles()){
                System.out.println("F: "+file);
                if(!file.isDirectory() &&  !file.getName().contains("BaseLibSpace") && file.getName().contains(".jar")){
                    Lib lib = addLib(file);
                    if(lib != null){lib.install(); }
                }
            }
        }
    }
    
    public static void reload() {
        libs.clear();
 
     
     FileFilter jarFilefilter  = (File file1) -> {
        
         return file1.getName().endsWith(".jar");
     }; 
     
    
     
//      for(File file : new File(Resource.getDiretorio()+"/Plugins").listFiles(jarFilefilter)){
//          System.out.println("File: "+file);
//    
//             Plugin plugin = loadPlugin(file);
//             if(plugin != null && plugin.install()){ 
//                 plugins.add(plugin);
//             }
//      
//      }
    
    
    }
    */
    public static String getInstanceFile(File file){
    
        if(file!= null && file.exists()){
            System.out.println("ZipFile: "+file);
        try {
            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry zipEntry = (ZipEntry) entries.nextElement();
			String name = zipEntry.getName();

			if (!zipEntry.isDirectory() && name.contains("InstanceLib")){
                                name = name.substring(0, name.lastIndexOf(".class"));
                                name = name.replace('/', '.');
				System.out.println(name);
                                return name;
			}
		}
        } catch (IOException ex) {

        }
    }
        return null;
    }

    public static Plugins installPlugins(File file) {


    // try {
       // Getting the jar URL which contains target class
        Plugins lib = null;
   
       if(file!=null && file.exists() && file.length()>0){
        URL[] classLoaderUrls = null;
    
        try {
            classLoaderUrls = new URL[]{file.toURI().toURL()};
        } catch (MalformedURLException ex) {

        }
         if(classLoaderUrls!=null) {
         
        // Create a new URLClassLoader 
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
        //System.out.println("urlClassLoader: "+urlClassLoader != null);
         
        // Crie uma nova instância da classe carregada
        Class<?> pluginClass = null;
        try {
            pluginClass = urlClassLoader.loadClass(getInstanceFile(file));
            System.out.println("Plugin: "+pluginClass != null);
        } catch (ClassNotFoundException ex) {

        }

        if(pluginClass!=null){
        // Create a new instance from the loaded class
        Constructor<?> constructor;
        try {
            constructor = pluginClass.getConstructor();
            //System.out.println("constructor: "+constructor != null); 
            Object libObj = constructor.newInstance();         
            //System.out.println("pluginObj: "+pluginObj != null);
            
           lib = (Plugins) libObj;
           if(lib!=null){      
               copyFile(file, new File(new File(System.getProperty("user.dir")), "lib"));
           }
//            Method method = pluginClass.getDeclaredMethod("instance");        
//   
//            lib =  (Lib) method.invoke(libObj);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException ex) {

        }
        }
    } }
         return lib;
    }
    /*
    public ArrayList<Lib> getALL(){    
        return libs;
    
    }
    
    */
    public static void copyFile(File source, File destination){
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
    
        if(!destination.exists()) destination.mkdirs();
        destination = new File(destination, source.getName());
            System.out.println("destination: "+destination);
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),      destinationChannel);
        }   catch (FileNotFoundException ex) {

            } catch (IOException ex) {

            } finally {

                try {
                    if (sourceChannel != null && sourceChannel.isOpen()) sourceChannel.close();              
                    if (destinationChannel != null && destinationChannel.isOpen()) destinationChannel.close();

            } catch (IOException ex) {

            }

       }
    }


}
