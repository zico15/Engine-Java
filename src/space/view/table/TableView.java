/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.view.table;


import space.view.table.renderer.MyTableModelBase;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JTable;

/**
 *
 * @author 35191
 */
public class TableView extends  JTable{
   
    private  MyTableModelBase modelView; 

    
    
    public TableView(){
         modelView = new MyTableModelBase(new String[]{"Propriedade","Value"});
        setDefaultRenderer(JComponent.class,modelView.getTableCellRenderer() );
        setDefaultEditor(JComponent.class, modelView.getTableCellEditorBase());
        setModel(modelView);
   
    }

    @Override
    public void repaint() {
        //setRowALL(gameObject);
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
        
    }

    
    
  
    
//  public ArrayList<BaseMenu>  setRowALL(GameObject gameObject){
//    this.gameObject = gameObject;
//    ArrayList<BaseMenu>  menuUI = new ArrayList<>();
//    if(getModelView()!=null){   
//        //removeRowSelectionInterval(0, getModelView().getRowCount());
//         removeAll();
//         updateUI();
//        getModelView().clean();
//    }
//        if(gameObject!= null && gameObject.getComponents()!= null)
//        {
//           
//      
//            gameObject.getComponents().values().stream().filter((component) -> (component != null && component.getMenuView()!=null && component.getMenuView().getMenu()!=null)).forEach(component -> {
//            BaseMenu item = component.getMenuView();
//            menuUI.add(item);
//            item.getMenu().forEach((itens) -> {
//            System.out.println("component: "+component.toString());
//               getModelView().addRow(itens);
//            });
//                          
//          });
//         
//    }
//       setModel(modelView);
//       return menuUI;
//  }

    /**
     * @return the modelView
     */
    public MyTableModelBase getModelView() {
        return modelView;
    }

    /**
     * @param modelView the modelView to set
     */
    public void setModelView(MyTableModelBase modelView) {
        this.modelView = modelView;
    }
  
}
