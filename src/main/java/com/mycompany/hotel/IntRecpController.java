/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel;

import API.Database;
import Model.Chambre;
import Model.Reservation;
import Model.client2;
import animatefx.animation.FadeInLeft;
import animatefx.animation.FadeInRight;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Color;
import static java.awt.Color.BLACK;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class IntRecpController implements Initializable {
    @FXML
    private JFXButton logOutB;
    @FXML
    private Pane PanReservation;
    @FXML
    private Pane RoomsPan;
    @FXML
    private Pane ClientsPan;
    @FXML
    private TableView<Reservation> Table_Reservation;
    @FXML
    private TableColumn<Reservation,String> ID_Reserv;
    @FXML
    private TableColumn<Reservation,String> Check_in;
    @FXML
    private TableColumn<Reservation,String> Check_out;
    @FXML
    private TableColumn<Reservation,String> Id_client;
    @FXML
    private TableColumn<Reservation,String> Id_chambre;
    
    ObservableList<Reservation> oblist = FXCollections.observableArrayList();    
    ObservableList<Chambre> oblist1 = FXCollections.observableArrayList();  
    ObservableList<client2> oblist2 = FXCollections.observableArrayList(); 
    @FXML
    private Label RNumber;
    @FXML
    private Pane AddRoomPan;
    @FXML
    private Pane UpdateRoomPan;
    @FXML
    private JFXButton Button_Delete;
    @FXML
    private JFXButton Update_Button;
    @FXML
    private TableView<Chambre> Table_Room;
    @FXML
    private TableColumn<Chambre,String> Id_Room;
    @FXML
    private TableColumn<Chambre,String> Capacity;
    @FXML
    private TableColumn<Chambre,String> Type;
    @FXML
    private TableColumn<Chambre,String> Cost;
    @FXML
    private JFXTextField CapacityTestF;
    @FXML
    private JFXComboBox<String> TypeCom;
    @FXML
    private JFXTextField CostText;
    @FXML
    private ProgressIndicator ProgressBar;
    @FXML
    private Label testL;
    @FXML
    private JFXTextField NewCapacity;
    @FXML
    private JFXComboBox<String> Com;
    @FXML
    private JFXTextField NewCost;
    @FXML
    private TableView<client2> Table_Clients;
    @FXML
    private TableColumn<client2,String> idclient;
    @FXML
    private TableColumn<client2,String> cinp;
    @FXML
    private TableColumn<client2,String> email;
    @FXML
    private TableColumn<client2,String> phone;
    @FXML
    private Pane GmailPane;
    @FXML
    private JFXTextField EmailTextField;
    @FXML
    private JFXPasswordField PasswordField;
    @FXML
    private Pane PaneSendEmail;
    @FXML
    private JFXTextArea Text;
    @FXML
    private JFXTextField Topic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        oblist.clear(); 
        try {
            Database.GetAllReservation(oblist);
            
        } catch (SQLException ex) {
            Logger.getLogger(IntClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        ID_Reserv.setCellValueFactory(new PropertyValueFactory<Reservation,String>("Id_Reservation"));  
        Check_in.setCellValueFactory(new PropertyValueFactory<Reservation,String>("check_in"));  
        Check_out.setCellValueFactory(new PropertyValueFactory<Reservation,String>("check_out"));  
        Id_client.setCellValueFactory(new PropertyValueFactory<Reservation,String>("ID_Client"));  
        Id_chambre.setCellValueFactory(new PropertyValueFactory<Reservation,String>("ID_Chambre"));   
        
       
        
        
        
        Table_Reservation.setItems(oblist);     
        
        // 
        
            oblist2.clear(); 
        try {
            Database.GetAllClients(oblist2);
            
        } catch (SQLException ex) {
            Logger.getLogger(IntClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
         idclient.setCellValueFactory(new PropertyValueFactory<client2,String>("idc"));  
         cinp.setCellValueFactory(new PropertyValueFactory<client2,String>("cin"));   
         
        email.setCellValueFactory(new PropertyValueFactory<client2,String>("email")); 
        
        phone.setCellValueFactory(new PropertyValueFactory<client2,String>("phone"));   
        
     
        
       
        
        
        
        Table_Clients.setItems(oblist2);  
        
        
       
        
        
        
        //Get and Set Total Reservation Number : 
        
        try { 
            int a =Database.GetNumberOfReservations();  
            RNumber.setText(""+a+"");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(IntRecpController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
      //  
         oblist1.clear();
          try {
            Database.getAllRooms(oblist1);
            
        } catch (SQLException ex) {
            Logger.getLogger(IntClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        Id_Room.setCellValueFactory(new PropertyValueFactory<Chambre,String>("ID_Chambre"));  
        Capacity.setCellValueFactory(new PropertyValueFactory<Chambre,String>("Capacity"));  
        Type.setCellValueFactory(new PropertyValueFactory<Chambre,String>("Type"));  
        Id_client.setCellValueFactory(new PropertyValueFactory<Reservation,String>("ID_Client"));  
        Cost.setCellValueFactory(new PropertyValueFactory<Chambre,String>("prix"));   
        
       
        
        
        
        Table_Room.setItems(oblist1);     
        
        //
        
        TypeCom.getItems().removeAll(TypeCom.getItems());
        TypeCom.getItems().addAll("Normal", "VIP", "Economy");
        TypeCom.getSelectionModel().select("Normal");  
        
        
        //  
         
      
         
        
        
        
        
        
        
        //
        
        try {
            // PieChart 
           // Double a = Double.valueOf(Database.GetNumberOfReservations()/Database.getNumberRooms()) ;  
           // this.testL.setText(""+(double)Database.GetNumberOfReservations()/(double)Database.getNumberRooms()+"");

            this.ProgressBar.setProgress(1-(double)Database.GetNumberOfReservations()/(double)Database.getNumberRooms());
        } catch (SQLException ex) {
            Logger.getLogger(IntRecpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
       
        
    }    

    @FXML
    private void ReservationAction(ActionEvent event) { 
        PanReservation.toFront();
    }

    @FXML
    private void RoomsAction(ActionEvent event) { 
        RoomsPan.toFront();
    } 

    @FXML
    private void CleintsAction(ActionEvent event) { 
        ClientsPan.toFront();
    }

    @FXML
    private void LogOutAction(ActionEvent event) throws IOException {  
         Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
                            Scene scene = new Scene(root);
                            scene.getStylesheets().add("/styles/Styles.css");     
                            
                            //close login interface 
                            Stage stage1 = (Stage)logOutB.getScene().getWindow();  
                            stage1.close();
                            
                            //display Admin interface
                            Stage stage = new Stage();
                     
                            stage.setScene(scene);
                            stage.show();    
    }

    @FXML
    private void CancelReservationAction(ActionEvent event) throws SQLException { 
        
        Reservation r = Table_Reservation.getSelectionModel().getSelectedItem(); 
        
        if(r == null){  
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("Please select a reservation to cancel");
                             alert.showAndWait(); 
            
        }else{  
                   
                       if(Database.DeleteReservation(r)){  
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("The reservation has been cancelled");
                             alert.showAndWait(); 
                           
                       }else{ 
                           
                       }
            
            
            
            
        }
    }

    @FXML
    private void RefreshAction(ActionEvent event) { 
        this.initialize(null, null);
    }

    @FXML
    private void AddAction(ActionEvent event) {  
        this.AddRoomPan.toFront();
        
    }

    @FXML
    private void DeleteAction(ActionEvent event) throws SQLException { 
        
        Chambre a = this.Table_Room.getSelectionModel().getSelectedItem();  
        
        
        if(a == null){ 
            
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("Please select a room to delete");
                             alert.showAndWait();   
            
            
            
                     }else{    
                             
                        if(Database.IStheRoomReserved(a)){  
                              
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("You can not delete this room because it has been reserved by a client");
                             alert.showAndWait(); 
                            
                            
                        }else{ 
                            if(Database.DeleteRoom(a)){   
                                
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("the room has been deleted");
                             alert.showAndWait(); 
                                
                            } 
                            
                        }
              
                
             
            
            
            
            
             }
        
        
        
        
        
    }

    @FXML
    private void RefreshRoomsAction(ActionEvent event) {
    }

    @FXML
    private void UpdateAction(ActionEvent event){   
           Chambre ch = this.Table_Room.getSelectionModel().getSelectedItem(); 
        
        if(ch == null ){  
            
                             Alert alert = new Alert(Alert.AlertType.WARNING);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("Please select a room to Update");
                             alert.showAndWait();   
            
        }else{ 
        
        Com.getItems().removeAll(TypeCom.getItems());
        Com.getItems().addAll("Normal", "VIP", "Economy");
        Com.getSelectionModel().select(ch.getType());  
        this.UpdateRoomPan.toFront();  
        
        } 
        
    }

    @FXML
    private void BackAction(ActionEvent event) {  
        this.NewCapacity.setText(null); 
        this.NewCost.setText(null); 
        
        this.RoomsPan.toFront();
    }

    @FXML
    private void AddRoomAction(ActionEvent event) throws SQLException {  
        
        int cap = Integer.parseInt(this.CapacityTestF.getText()); 
        int cos =  Integer.parseInt(this.CostText.getText());   
        String  type = this.TypeCom.getSelectionModel().getSelectedItem();  
        Random rnd = new Random();
        int id = 100000 + rnd.nextInt(900000);    
        
        
        
        if(Database.AddRoom(new Chambre(id,type,cap,cos))){  
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("the room has been inserted successfully");
                             alert.showAndWait();   
                             
                             this.CapacityTestF.setText(null); 
                             this.CostText.setText(null); 
                             
                             this.RoomsPan.toFront(); 
                             this.initialize(null, null); 
                             
                         //    this.PieChartRooms
                             
            
            
        }
       
        
        
        
    }

    @FXML
    private void UpAction(ActionEvent event) throws SQLException {  
         Chambre ch = this.Table_Room.getSelectionModel().getSelectedItem();  
         
         
        
        
        if(this.NewCapacity.getText().isEmpty() || this.NewCost.getText().isEmpty()){  
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("please enter all the informations");
                             alert.showAndWait();   
            
        }else{
            
            
               if(Database.UpdateRoom(ch,new Chambre(123456,Com.getSelectionModel().getSelectedItem(),Integer.parseInt(NewCapacity.getText()),Integer.parseInt(NewCost.getText())))){ 
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("The room informations has been Updated");
                             alert.showAndWait();    
                             
                             this.RoomsPan.toFront(); 
                             this.initialize(null, null);
               }
                             
             
        }
            
        
    }

    @FXML
    private void ConnectionAction(ActionEvent event) { 
        String em = this.EmailTextField.getText(); 
        String pas = this.PasswordField.getText();  
        
        
        
        if( this.EmailTextField.getText().isEmpty() ||this.PasswordField.getText().isEmpty() ){   
             
                             Alert alert = new Alert(Alert.AlertType.ERROR);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("Please enter all the informations !");
                             alert.showAndWait();    
             
            
                            }else{     
            
              
                             new FadeInRight(PaneSendEmail).play();       
                             this.PaneSendEmail.toFront();  
        
                            
            
                             
            
            
                    }
    }


    @FXML
    private void ExitAction(ActionEvent event) { 
        this.EXITACTION(event);
    }

    @FXML
    private void ContactAction(ActionEvent event) {    
        
        client2 a = this.Table_Clients.getSelectionModel().getSelectedItem(); 
        
        if(a == null ){  
             
                             Alert alert = new Alert(Alert.AlertType.ERROR);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("Please select a client");
                             alert.showAndWait();    
                             
                             
        }else{
        
        new FadeInRight(GmailPane).play();   
        this.GmailPane.toFront();  
        
        }
        
        
    }

    @FXML
    private void EXITACTION(ActionEvent event) { 
        
         new FadeInLeft(ClientsPan).play();   
        this.ClientsPan.toFront();  
        
    }

    @FXML
    private void SendAction(ActionEvent event) {   
          
            String host = "smtp.gmail.com";  
  final String user=this.EmailTextField.getText()   ;  
  final String password=this.PasswordField.getText();
    
  String to= this.Table_Clients.getSelectionModel().getSelectedItem().getEmail();
  Properties props = new Properties();  
  
   
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.port", "465");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.prot", "465");
    
   
     
 
   
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject(this.Topic.getText());  
     message.setText(this.Text.getText());  
       
    //send the message  
     Transport.send(message);  
  
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("Your email has ben sent successfelly");
                             alert.showAndWait();     
                             
                             this.EXITACTION(event);
   
     } catch (MessagingException e) {e.printStackTrace();}  
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}
