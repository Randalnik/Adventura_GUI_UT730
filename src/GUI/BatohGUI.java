/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import logika.Hra;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/*******************************************************************************
 * Instance třídy {@code RaketaUI} představují ...
 *
 * @author    Jiří Zahálka
 * @version   1.0
 */
public class BatohGUI extends AnchorPane implements Observer {

    private IHra hra;
    private Map<String, Vec> polozky = new HashMap<String, Vec>();
//    private List<ImageView> obrazkyMimozemstanu = new ArrayList<ImageView>();
    private List<HBox> radky = new ArrayList<HBox>();
    private Main main;

    private VBox vbox;

    /**
    *  Konstruktor třídy
    *  
    *  @param hra hra, která obsahuje objekty celé adventury, které se dále z této třídy volají
    */   
    public BatohGUI (IHra hra, Main main) {
        
        this.hra = hra;
        this.main = main;
        hra.getHerniPlan().registerObserver(this);
        hra.getHerniPlan().getBatoh().registerObserver(this);
       
        // Vbox
        vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.setMinWidth(275);

        // Label
        Label zadejPrikazLabel = new Label("Tvoje vybavení");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    
        vbox.getChildren().add(zadejPrikazLabel);
        this.getChildren().add(vbox);    
        
        update();
   }
          
     /**
     * Metoda bez parametru
     * tato metoda je zavolána, když je ze Subjectu zavoláno notifyAllObservers();
     *
     */
    @Override
   public void update() {
       
        vbox.getChildren().removeAll(radky);
        radky.clear();
        
        polozky = hra.getHerniPlan().getBatoh().getSeznamVeci();

        int i = 0;

        for (Vec objekt : polozky.values()) {
            
            HBox hbox = new HBox();
            hbox.setPadding(new Insets(0));
            hbox.setSpacing(10);
            hbox.setMinWidth(275);
            
            ImageView obrazek = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/nuz.jpg"), 75, 75, false, false));
            
            double top = i * 75;
            this.setTopAnchor(obrazek, top);
            
            Button btn = new Button("Vyhoď");
            
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (hra.konecHry()) { return; }
                    main.getCenterText().appendText ("\n\n");
                    main.getCenterText().appendText(hra.zpracujPrikaz("odhod "+ objekt.getNazev()));
                }
            });
            
            hbox.getChildren().addAll(obrazek, btn);
            radky.add(hbox);
            
            i++;
        } 
        
        vbox.getChildren().addAll(radky);
//        hra.getHerniPlan().notifyAllObservers();
    }

     /**
     * Metoda na obnovení hry
     *  @param hra hra, která obsahuje objekty celé adventury, které se dále z této třídy volaji
     */
   
    //@Override
    public void novaHra(IHra hra) {
        
        hra.getHerniPlan().getBatoh().deleteObserver(this);
        hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        hra.getHerniPlan().getBatoh().registerObserver(this);
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
}
//    *metoda update aktualizuje obsah batohu
//    */
//    @Override
//    public void update() {           
//        Map<String, Vec> veciVBatohu = hra.getHerniPlan().getBatoh().getSeznamVeci();
//        
//        dataBatohu.clear();
//        
//        for(Vec vec : veciVBatohu.values()){
//            dataBatohu.add(new AnchorPane(new ImageView(vec.getImg())));
//            
//        }
//    }