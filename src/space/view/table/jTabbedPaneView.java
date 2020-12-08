/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.view.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JWindow;

/**
 *
 * @author 35191
 */
public class jTabbedPaneView extends JTabbedPane{

    
    public static final JWindow dialog = new JWindow();
    public static final JWindow dialog2 = new JWindow();
    public static final JLabel label = new JLabel(); 
    public static final ArrayList<jTabbedPaneView> list = new ArrayList<>();
    public static jTabbedPaneView tableSelect;
    
    Point tabPt;

    public jTabbedPaneView() {
  
        

        list.add(this);
        Handler h = new Handler(this);
        addMouseListener(h);
        addMouseMotionListener(h);
        addPropertyChangeListener(h);
        setFocusable(false);
        //label.setIcon(new ImageIcon(makeDragTabImage(this)));
          // label.setText("sdadssd");
        
        dialog.add(label);
        //dialog.setOpacity(.5f);
        
        dialog.setSize(200, 100); 
        dialog.pack();
        //dialog.setVisible(true);
        
        dialog2.setSize(200, 100); 
        dialog2.pack();
        
         System.out.println("dialog: ");
        
    }
     
    private BufferedImage makeDragTabImage(jTabbedPaneView tabbedPane) {
    Rectangle rect = tabbedPane.getBoundsAt(tabbedPane.getSelectedIndex());
    BufferedImage image = new BufferedImage(tabbedPane.getWidth(), tabbedPane.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics g = image.createGraphics();
    tabbedPane.paint(g);
    g.dispose();
    if (rect.x < 0) {
      rect.translate(-rect.x, 0);
    }
    if (rect.y < 0) {
      rect.translate(0, -rect.y);
    }
    if (rect.x + rect.width > image.getWidth()) {
      rect.width = image.getWidth() - rect.x;
    }
    if (rect.y + rect.height > image.getHeight()) {
      rect.height = image.getHeight() - rect.y;
    }
    return image.getSubimage(rect.x, rect.y, rect.width, rect.height);
  }
 
//
    private class Handler extends MouseAdapter implements PropertyChangeListener { 
        
        private final jTabbedPaneView tabbedPane;
        private int indexSelect;
        private Component componentSelect;
        public Handler(jTabbedPaneView tabbedPane) {
            this.tabbedPane=tabbedPane;
        }

        
        public void updadeGraphicsSelect(JTabbedPane pane,Component componentSelect,Rectangle r,Point point){
           Graphics g = dialog2.getGraphics();

            g.setColor(Color.red);
            g.fillRect(0, 0, 5, r.height);
   
                    dialog2.setLocation(pane.getLocationOnScreen().x+r.x,pane.getLocationOnScreen().y+r.y);

          
            dialog2.setSize(5, r.height); 
            dialog2.setVisible(true);

        }
      

    // MouseListener
    @Override 
    public void mousePressed(MouseEvent e) {

      label.setIcon(new ImageIcon(makeDragTabImage(tabbedPane)));
      dialog.setLocation(e.getLocationOnScreen().x + 10,e.getLocationOnScreen().y + 5);
      //tab = tabbedPane.getTabComponentAt(tabbedPane.getSelectedIndex());
       dialog.pack();
       dialog.setVisible(false);
       dialog2.setVisible(false);
    
     
    }

    @Override 
    public void mouseDragged(MouseEvent e) {
     //System.out.println("mouseDragged");
     
      dialog.setLocation(e.getLocationOnScreen().x + 10,e.getLocationOnScreen().y + 5);
      dialog.setVisible(true);
      
        Point point = new  Point(e.getX()+tabbedPane.getX(), e.getY()+tabbedPane.getY());
    
      Rectangle rectangle = new  Rectangle( );
      
      for(jTabbedPaneView t : list){
          rectangle.setRect(t.getX(), t.getY(), t.getWidth(), t.getHeight());
          if(rectangle.contains(point)){
             
             

                  point.setLocation(e.getX()- ( t.getX() -tabbedPane.getX()) ,e.getY()- (t.getY() - tabbedPane.getY()));
              
                  if(t.getTabCount() == 0){
                    tableSelect = t;
                    break;
                  }else{
                 for(int index=0;index < t.getTabCount();index++){
                     
                   tableSelect = null;
                    if(t.getBoundsAt(index).contains(point)){
                        tableSelect = t;
                        indexSelect = index;
                        updadeGraphicsSelect(t,t.getTabComponentAt(index),t.getBoundsAt(index),e.getLocationOnScreen());
                        System.out.println("T: "+t.getName());
                         break;
                    }
                    
                }}
              
            
        
          }
          
           //System.out.println("T: "+t.getLocationOnScreen().distance(e.getLocationOnScreen()));
       
      }
   
    }

        @Override
        public void mouseReleased(MouseEvent e) {
              //System.out.println("mouseReleased");
                dialog.setVisible(false);
                dialog2.setVisible(false);
                System.out.println("tableSelect: "+(tableSelect!=null) );
               if(tableSelect!=null){
                   Component c = tabbedPane.getSelectedComponent();
                   String title = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());       
                   Icon icon = getIconAt(tabbedPane.getSelectedIndex());
                   String tip = getToolTipTextAt(tabbedPane.getSelectedIndex());
                   
                   tabbedPane.remove(c);      
                   if(tableSelect.getTabCount() == 0){
                   tableSelect.addTab(title, icon, c, tip);
                   }else {
                   tableSelect.insertTab(title, icon, c, tip, indexSelect);
                   }
//                   tableSelect.add(c, indexSelect);
//                   tableSelect.setTitleAt(indexSelect, title);
//                   tableSelect.setIconAt(indexSelect, icon);
//                   tableSelect.setToolTipTextAt(indexSelect, tip);
                   tableSelect.setSelectedComponent(c);
               }
            
               tableSelect= null;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
          
        }
    
   
    
  }
    
    
}
