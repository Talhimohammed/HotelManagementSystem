package com.mycompany.hotel;

import animatefx.animation.Bounce;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeInDown;
import animatefx.animation.FadeInLeft;
import animatefx.animation.FadeInRight;
import animatefx.animation.Flash;
import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class FXMLController implements Initializable {
    
    private Label label;
    @FXML
    private Pane SignPan;
    @FXML
    private Pane WelcomePan;
    @FXML
    private JFXButton BtnContinue;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello");  
        System.out.println("hello"); 
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ContinueClick(ActionEvent event) {     
        
        new FadeInRight(SignPan).play();
        SignPan.toFront(); 
       
    }
} 
