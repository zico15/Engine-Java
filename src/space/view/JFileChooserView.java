/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.view;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author 35191
 */
public class JFileChooserView {
    
    
    public static File[] getFiles(String title,String directory,FileNameExtensionFilter filter){
        JFileChooser jfc = new JFileChooser(directory != null ? new File(directory) : FileSystemView.getFileSystemView().getHomeDirectory());
        
        jfc.setDialogTitle(title);
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setMultiSelectionEnabled(true);
      
        if(filter!=null)jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            
            if(jfc.getSelectedFiles() == null || jfc.getSelectedFiles().length == 0){
             return new File[]{jfc.getSelectedFile()};
            }
            return  jfc.getSelectedFiles();            
        }
        
        return null;
    }
    
    
    public static FileNameExtensionFilter creadFilter(String title,String extencao){    
        return  new FileNameExtensionFilter(title, extencao);
    }
    
}
