/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.IHra;

import logika.*;
import uiText.TextoveRozhrani;
       
/**
 *
 * @author xzenj02
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Start adventura");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                IHra hra = new Hra();
                
                BorderPane borderPane = new BorderPane();
                TextArea centerText = new TextArea();
                centerText.setText(hra.vratUvitani());
                centerText.setEditable(false);
                borderPane.setCenter(centerText);
                
                Label zadejPrikazLabel = new Label("Zadej prikaz");
                zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                
                TextField zadejPrikazTextField = new TextField ("Sem zadej prikaz");
                zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
 
                    String zadanyPrikaz = zadejPrikazTextField.getText();
                    String odpoved = hra.zpracujPrikaz(zadanyPrikaz);
                    
                    centerText.appendText("\n\n" + zadanyPrikaz + "\n\n");
                    centerText.appendText("\n\n" + odpoved + "\n\n");
                    
                    zadejPrikazTextField.setText("");
                    
                    if (hra.konecHry()) {
                        
                        zadejPrikazTextField.setEditable(false);
                    }                   
                    }
                });
                
                
               
                           
                FlowPane dolniPanel = new FlowPane();
                dolniPanel.setAlignment(Pos.CENTER);
                dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
                borderPane.setBottom(dolniPanel);
                
                Scene scene = new Scene (borderPane, 1000, 500);
                
                primaryStage.setTitle("Moje adventura - ");
                primaryStage.setScene(scene);
                primaryStage.show();
                
                
                
                
             
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else {
            if (args[0].equals("-text")) {
                
                IHra hra = new Hra();
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();
            }
            else {
                
                System.out.println("Neplatny parametr");
                System.exit(1);
                
            }
        }
    }
  
    
}
