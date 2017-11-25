/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import GUI.MapaGUI;
import GUI.BatohGUI;
import GUI.VychodyGUI;
import GUI.ObjektyGUI;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import uiText.*;
import logika.*;
import static javafx.application.Application.launch;
import javafx.geometry.Orientation;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;

/**
 *
 * @author xzenj02
 */
public class Main extends Application {

    
    private MenuPole menu;
    private IHra hra;
    private TextArea centerText;
    private Stage primaryStage;
    
    private MapaGUI mapa;
    private BatohGUI obsahBatoh;
    private VychodyGUI vychody;
    private ObjektyGUI objekty;
    
    public TextArea getCenterText() {
        return centerText;
    }
       
    @Override
    public void start(Stage primaryStage) {
        hra = new Hra();
        
        this.primaryStage = primaryStage;
        
        mapa = new MapaGUI(hra) {};
        menu = new MenuPole(this);
        vychody = new VychodyGUI(hra, this);
        obsahBatoh = new BatohGUI(hra, this);
        objekty = new ObjektyGUI(hra, this);
        
        BorderPane borderPane = new BorderPane();
        
        centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false);
        borderPane.setCenter(centerText);
        
        Label zadejPrikazLabel = new Label("Příkaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        TextField zadejPrikazTextField = new TextField("");
        
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String zadanyPrikaz = zadejPrikazTextField.getText();
                String odpoved = hra.zpracujPrikaz(zadanyPrikaz);
                
                centerText.appendText("\n" + zadanyPrikaz + "\n");
                centerText.appendText("\n" + odpoved + "\n");
                
                zadejPrikazTextField.setText("");
                
                if(hra.konecHry()){
                    zadejPrikazTextField.setEditable(false);
                }
                
            }
        });
        
        FlowPane dolniPanel = new FlowPane();
        dolniPanel.setAlignment(Pos.CENTER);
        dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        
        //panel prikaz
        borderPane.setBottom(dolniPanel);
        //obrazek s mapou
        borderPane.setLeft((Node) mapa);
        //menu adventury
        borderPane.setTop(menu);
        // batoh s obsahem
        FlowPane pravyPanel = new FlowPane(Orientation.VERTICAL);
        pravyPanel.getChildren().addAll(objekty, obsahBatoh, vychody);
        
        borderPane.setRight(pravyPanel);
        
        
        // BASE SCENE
        Scene scene = new Scene(borderPane, 1300,620);

        primaryStage.setTitle("Adventura - Přežij Ostrov!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        zadejPrikazTextField.requestFocus();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();
            } else {
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    public void novaHra() {
        hra = new Hra();
        centerText.setText(hra.vratUvitani());
        //to same pro vsechny observery
        mapa.novaHra(hra);
        objekty.novaHra(hra);
        obsahBatoh.novaHra(hra);
        vychody.novaHra(hra);
    }

    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public BatohGUI getPravaStrana(){
        return obsahBatoh;
    }
    

}
