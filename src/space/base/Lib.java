/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.base;

import java.io.Serializable;

/**
 *
 * @author 35191
 */
public abstract class Lib implements Serializable{
    
    
    public String name;
    public String version;
    public String data;
    public String author;
   
    public abstract boolean install();
    
    public abstract boolean uninstall();
    
    @Override
    public String toString() {     
        
        return String.format("Lib: {} \nVersion: {} \nData: {} \nAuthor: {}",name,version,data,author);
    }
    
    
    public Lib instance(){ return this;}
    
    
}
