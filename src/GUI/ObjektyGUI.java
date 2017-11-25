/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import logika.IHra;
import utils.Observer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.AnchorPane;
import logika.Vec;
import main.Main;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*******************************************************************************
 * Instance třídy {@code ProstorUI} představují ...
 *
 * @author    Jiří Zahálka
 * @version   1.0
 */
public class ObjektyGUI extends AnchorPane implements Observer {
    
    private IHra hra;
    private Map<String, Vec> veci = new HashMap<String, Vec>();
    private List<ImageView> obrazkyObjektu = new ArrayList<ImageView>();
    private List<HBox> radky = new ArrayList<HBox>();

    private VBox vbox;
    private Main main;

    /**
    *  Konstruktor třídy
    *  
    *  @param hra hra, která obsahuje objekty celé adventury, které se dále z této třídy volají
    */   
    public ObjektyGUI(IHra hra, Main main) {
        
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
        Label zadejPrikazLabel = new Label("Objekty v prostoru");
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
        
        veci = hra.getHerniPlan().getAktualniProstor().ziskejVeci();
        
        int i = 0;
        
        for (Vec objekt : veci.values()) {
            
            String soubor = objekt.muzuZvednout()? "/zdroje/klic.png" : "/zdroje/voda.png";
            
            HBox hbox = new HBox();
            hbox.setPadding(new Insets(0));
            hbox.setSpacing(10);
            hbox.setMinWidth(275);
        
            ImageView obrazek = new ImageView(new Image(Main.class.getResourceAsStream(soubor), 75, 75, false, false));
            
            double top = i * 75;
            this.setTopAnchor(obrazek, top);            

            Button btn = new Button("Seber");
            Button hled = new Button ("Prohledej");
            
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (hra.konecHry()) { return; }
                    main.getCenterText().appendText ("\n\n");
                    main.getCenterText().appendText(hra.zpracujPrikaz("seber " + objekt.getNazev()));      
                    
                    
                    if (!hra.getHerniPlan().getBatoh().getSeznamVeci().containsValue(objekt)) {
                     
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Informace");
                        alert.setHeaderText("Tento objekt nelze přidat");
                        alert.setContentText("Tento objekt není přenositelný");
                        
                        alert.showAndWait();
                    }
                }
            });
            
            hled.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (hra.konecHry()) { return; }
                    main.getCenterText().appendText ("\n\n");
                    main.getCenterText().appendText(hra.zpracujPrikaz("prohledej " + objekt.getNazev()));      
                    
                    
                    if (!hra.getHerniPlan().getBatoh().getSeznamVeci().containsValue(objekt)) {
                     
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Informace");
                        alert.setHeaderText("Tento objekt nelze prohledat.");
                        alert.setContentText("Tento objekt není možné prohledat.");
                        
                        alert.showAndWait();
                    }
                }
            });

            hbox.getChildren().addAll(obrazek, btn, hled);
            radky.add(hbox);
            
            i++;
        }
        
        vbox.getChildren().addAll(radky);
    }
    
     /**
     * Metoda na obnovení hry
     *  @param hra hra, která obsahuje objekty celé adventury, které se dále z této třídy volaji
     */
    //@Override
    public void novaHra(IHra hra) {

        hra.getHerniPlan().deleteObserver(this);
        hra.getHerniPlan().getBatoh().registerObserver(this);

        this.hra = hra;
        
        hra.getHerniPlan().registerObserver(this);
        hra.getHerniPlan().getBatoh().registerObserver(this);
        update();
    }
}