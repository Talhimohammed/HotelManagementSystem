package com.mycompany.hotel;

import API.Database;
import Model.User;
import Model.client;
import animatefx.animation.Bounce;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeInDown;
import animatefx.animation.FadeInLeft;
import animatefx.animation.FadeInRight;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    private Label label;
    @FXML
    private Pane SignPan;
    @FXML
    private Pane WelcomePan;
    @FXML
    private JFXButton BtnContinue;
    @FXML
    private Pane PanSignUp;
    @FXML
    private Pane PanSinIn;
    @FXML
    private JFXButton NewClient;
    @FXML
    private Label Con;
    @FXML
    private JFXTextField loginTextField;
    @FXML
    private JFXPasswordField passwordTextField;
    @FXML
    private JFXButton Loginbutton;
    @FXML
    private Label LabelLogin;
    @FXML
    private Label PasswordLabel;
    @FXML
    private JFXTextField TextFullName;
    @FXML
    private JFXPasswordField TextPassword;
    @FXML
    private JFXTextField TextIndex;
    @FXML
    private JFXTextField TextLogin;
    @FXML
    private Pane PersonalDataPan;
    @FXML
    private JFXButton Loginbutton1;
    @FXML
    private JFXTextField CinTextField;
    @FXML
    private JFXTextField EmailTextField;
    @FXML
    private JFXTextField PhoneTextField;
    @FXML
    private Label labeltest;
    
    
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

    @FXML
    private void toSingUpPan(ActionEvent event) {   
         new FadeIn(PanSignUp).play();  
         PanSignUp.toFront();
         
        
    }

    @FXML
    private void BackToSignIn(MouseEvent event) {  
         this.CinTextField.setText(null); 
         this.EmailTextField.setText(null); 
         this.PhoneTextField.setText(null); 
                 
          new FadeIn(PanSinIn).play();  
         PanSinIn.toFront(); 
         
    } 

    @FXML
    private void ConnectionLogin(ActionEvent event) throws SQLException, IOException { 
               String login = loginTextField.getText(); 
               String password = passwordTextField.getText();
               Connection conn = (Connection) Database.getConnection();  
               Statement stmt = conn.createStatement(); 
               ResultSet rs = stmt.executeQuery("SELECT type FROM utilisateur WHERE login ="+"'"+login+"'"+"And password ="+"'"+password+"'");
               if(login.isEmpty() || password.isEmpty()){ 
                       Alert alert = new Alert(AlertType.INFORMATION);
                       alert.setTitle("Error");
                       alert.setHeaderText(null);
                       alert.setContentText("please enter all the informations");
                       alert.showAndWait(); 
               }else{ 
             
                
               if(rs.next()){   
                    String type = rs.getString("type");
                    if(type.equals("Admin")){ 
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/fxml/InterAdmin.fxml"));
                            Scene scene = new Scene(root);
                            scene.getStylesheets().add("/styles/Styles.css");     
                            
                            //close login interface 
                            Stage stage1 = (Stage)Loginbutton.getScene().getWindow();  
                            stage1.close();
                            
                            //display Admin interface
                            Stage stage = new Stage();
                            stage.setTitle("Administrateur");
                            stage.setScene(scene);
                            stage.show();   
                            
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else if(type.equals("Client")){ 
                         
                            try {
                             
                            FXMLLoader Loader = new FXMLLoader(); 
                            Loader.setLocation(getClass().getResource("/fxml/IntClient.fxml"));
                              
                            
                            Loader.load(); 
                            IntClientController display = Loader.getController() ;
                            display.setLogin(login);  
                            
                            Stage stage1 = (Stage)Loginbutton.getScene().getWindow();  
                            stage1.close();
                            
                            Parent p = Loader.getRoot();
                            Stage stage = new Stage(); 
                            stage.setScene(new Scene(p)); 
                            stage.showAndWait();
                           
                            
                            // 
                            
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        
                        
                        
                    }else if(type.equals("Receptionist")){   
                        
                        Parent root = FXMLLoader.load(getClass().getResource("/fxml/IntRecp.fxml"));
                            Scene scene = new Scene(root);
                            scene.getStylesheets().add("/styles/Styles.css");     
                            
                            //close login interface 
                            Stage stage1 = (Stage)Loginbutton.getScene().getWindow();  
                            stage1.close();
                            
                            //display Admin interface
                            Stage stage = new Stage();
                            stage.setTitle("Receptionist");
                            stage.setScene(scene);
                            stage.show();   
                        
                        
                        
                    }else if(type.equals("Cleaning chief")){  
                         Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChefP.fxml"));
                            Scene scene = new Scene(root);
                            scene.getStylesheets().add("/styles/Styles.css");     
                            
                            //close login interface 
                            Stage stage1 = (Stage)Loginbutton.getScene().getWindow();  
                            stage1.close();
                            
                            //display Admin interface
                            Stage stage = new Stage();
                            stage.setTitle("Receptionist");
                            stage.setScene(scene);
                            stage.show();  
                        
                        
                        
                    }
               }else{ 
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Authentification error ");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter login or password correctly ! ");
                    alert.showAndWait(); 
               }
                       
               }
    }

    @FXML
    private void SignUpAction(ActionEvent event) throws SQLException {  
         String Login = TextLogin.getText(); 
          String FullN = TextFullName.getText();  
          String Index = TextIndex.getText(); 
          String Password = TextPassword.getText(); 
         
        /*   User u1 = new User(Login,Password,Index,FullN,"Client");   
           
            if(Database.AddUser(u1)){ 
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Welcome , your account has ben creadted successfully , you can now login in ");
                    alert.showAndWait();     
                 
            }*/ 
          
          if (FullN.isEmpty() || Login.isEmpty() || Index.isEmpty() || Password.isEmpty()){
                  
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter all the informations !");
                    alert.showAndWait();   
     
              }else{   
               
                       new FadeInRight(PersonalDataPan).play();
                       PersonalDataPan.toFront(); 
    
        
            }  

    

    }

    @FXML
    private void FinishAction(ActionEvent event) throws SQLException { 
        
        String CINp = CinTextField.getText(); 
        String Email = EmailTextField.getText(); 
        String Phone = PhoneTextField.getText(); 
        
      
        
        client c = new client(this.TextLogin.getText(),this.TextPassword.getText(),this.TextIndex.getText(),this.TextFullName.getText(),"Client",this.CinTextField.getText(),this.EmailTextField.getText(),this.PhoneTextField.getText());
        User a = (User)c ; 
        
        labeltest.setText(((User)c).getPassword());
       if(CINp.isEmpty() || Email.isEmpty() || Phone.isEmpty()){  
            
             
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter all the informations !");
                    alert.showAndWait();   
            
        }else{
              
                    if(Database.AddClient(c) && Database.AddUser(a)){ 
                             Alert alert = new Alert(AlertType.INFORMATION);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("Welcome , your account has ben creadted successfully , you can now login in ");
                             alert.showAndWait();    
                    }else{ 
                              Alert alert = new Alert(AlertType.INFORMATION);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("NOOOOOOOOOOOOOOOOO ");
                             alert.showAndWait();  
                    }            
            
        } 
             
    } 

    @FXML
    private void CancelAction(ActionEvent event) { 
        
         new FadeIn(PanSinIn).play();  
         PanSinIn.toFront(); 
         
         this.CinTextField.setText(null); 
         this.EmailTextField.setText(null); 
         this.PhoneTextField.setText(null); 
         this.TextFullName.setText(null);  
         this.TextLogin.setText(null); 
         this.TextPassword.setText(null); 
         this.TextIndex.setText(null);
         
        
    } 
 
  

  
   
       
}
