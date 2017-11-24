/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import utils.Observer;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import logika.*;
import main.Main;

/**
 *
 * @author Jakub Skála
 */
public class PravaStrana implements Observer {
    
    private IHra hra;
    private ListView <AnchorPane> listBatohu;
    private ObservableList <AnchorPane> dataBatohu;
    private FlowPane pane;
    
    public void setHra (IHra hra){
        this.hra = hra;
    }
    
      /*
    *konstruktor třidy PravaStrana
    *@param hra instance tridy implementujici rozhrani IHra
    */
    public PravaStrana(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().getBatoh().registerObserver(this);
        spust();
    }
    /*
    *metoda update aktualizuje obsah batohu
    */
    @Override
    public void update() {           
        Map<String, Vec> veciVBatohu = hra.getHerniPlan().getBatoh().getSeznamVeci();
        
        dataBatohu.clear();
        
        for(Vec vec : veciVBatohu.values()){
            dataBatohu.add(new AnchorPane(new ImageView(vec.getImg())));
            
        }
    }
    /*
    *metoda spust vklada predmety do batohu
    */
    private void spust() {
       pane = new FlowPane();
       listBatohu = new ListView();
       dataBatohu = FXCollections.observableArrayList();
       
       listBatohu.setPrefWidth(130);
       listBatohu.setItems(dataBatohu);      
              
       
       pane.getChildren().addAll(listBatohu);
       
       update();
       
    }
    
    
    public FlowPane getPanel(){
        return pane;
    }

    
}
