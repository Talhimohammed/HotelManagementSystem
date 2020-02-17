/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel;

import API.Database;
import Model.Chambre;
import Model.Reservation;
import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class IntClientController implements Initializable {
    @FXML
    private Pane ReserveRomPan;
    @FXML
    private Pane ReservationPan;
    @FXML
    private Pane UpdatePan;

    /**
     * Initializes the controller class.
     */ 
    
     ObservableList<Chambre> oblist = FXCollections.observableArrayList();   
     ObservableList<Reservation> oblist1 = FXCollections.observableArrayList();  
    @FXML
    private TableView<Chambre> TableRooms;
    @FXML
    private TableColumn<Chambre,String> IdRoomCol;
    @FXML
    private TableColumn<Chambre,String> CapacityCol;
    @FXML
    private TableColumn<Chambre,String> TypeCol;
    @FXML
    private TableColumn<Chambre,String> CostCol;
    @FXML
    private JFXButton SubmitButton; 
    
 
    private String login;
    private Label loginlabel;
    @FXML
    private JFXDatePicker CheckInDate;
    @FXML
    private JFXDatePicker CheckOutDate;
    @FXML
    private Label Label;
    @FXML
    private TableView<Reservation> Reservation_Table;
    @FXML
    private TableColumn<Reservation,String> IdR_Col;
    @FXML
    private TableColumn<Reservation,String> ChIn_Col;
    @FXML
    private TableColumn<Reservation,String> ChOut_Col;
    @FXML
    private JFXDatePicker InDate;
    @FXML
    private JFXDatePicker OutDate;
    @FXML
    private Pane HotelAddressPan;
    @FXML
    private TableColumn<Reservation,String> IdRoom_Col;
    @FXML
    private JFXButton logOutB;
    @FXML
    private Label labelprice;
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
         
     oblist.clear();  
     oblist1.clear();
        try {
            Database.getAllRooms(oblist); 
            
        } catch (SQLException ex) {
            Logger.getLogger(IntClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        IdRoomCol.setCellValueFactory(new PropertyValueFactory<Chambre,String>("ID_Chambre"));  
        CapacityCol.setCellValueFactory(new PropertyValueFactory<Chambre,String>("Capacity"));  
        TypeCol.setCellValueFactory(new PropertyValueFactory<Chambre,String>("Type"));  
        CostCol.setCellValueFactory(new PropertyValueFactory<Chambre,String>("prix"));   
        
        
        
        TableRooms.setItems(oblist); 
         
           
        
        
     }  
    
   
    @FXML
    private void ReserveRomAction(ActionEvent event) { 
        this.ReserveRomPan.toFront();
    }

    @FXML
    private void YReservationAction(ActionEvent event) { 
        this.ReservationPan.toFront();   
        
            oblist1.clear(); 
        try {
              Database.getReservationsByClient(Database.getIDclient(login),oblist1);
            
        } catch (SQLException ex) {
            Logger.getLogger(IntClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        IdR_Col.setCellValueFactory(new PropertyValueFactory<Reservation,String>("Id_Reservation"));  
        ChIn_Col.setCellValueFactory(new PropertyValueFactory<Reservation,String>("check_in"));  
        ChOut_Col.setCellValueFactory(new PropertyValueFactory<Reservation,String>("check_out"));   
        IdRoom_Col.setCellValueFactory(new PropertyValueFactory<Reservation,String>("ID_Chambre"));
      
        
        
        Reservation_Table.setItems(oblist1); 
        
        
        
        
        
        
        
        
    }

    @FXML
    private void UpdateAction(ActionEvent event) { 
        this.UpdatePan.toFront();
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
    private void SubmitAction(ActionEvent event) throws SQLException {  
        
          Chambre reserv = TableRooms.getSelectionModel().getSelectedItem();  
          
         
        
         if(reserv == null){  
             
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("");
                    alert.setHeaderText("");
                    alert.setContentText("Please select room !");
                    alert.showAndWait();     
             
         }else{
       
               try{ 
                         Date che1 = Date.valueOf(this.CheckInDate.getValue()); 
       
                         Date che2 = Date.valueOf(this.CheckOutDate.getValue());    
                         
                          int ID_Chambre = reserv.getID_Chambre();
                        
                         
                            Random rnd = new Random();
                            int id = 100000 + rnd.nextInt(900000);                  //generate a random id reservation 
                         
                         Reservation r = new Reservation(id,che1,che2,Database.getIDclient(login),ID_Chambre); 
                         
                    if(Database.isRoomReserved(reserv.getID_Chambre(), che1, che2)){  
                        
                           Alert alert = new Alert(AlertType.WARNING);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("the room is already reserved ! ");
                             alert.showAndWait();       
                             
                        
                    }else{
                         if(Database.AddReservation(r)){  
                             
                             Alert alert = new Alert(AlertType.INFORMATION);
                             alert.setTitle("");
                             alert.setHeaderText(null);
                             alert.setContentText("the room has been reserved successfully ! ");
                             alert.showAndWait();       
                             
                             this.RefreshAction(event);
                             
                        /*     oblist.clear(); 
                             this.initialize(null, null); */
                             
                         }  
                         
                    }
                         
                         
                         
                         
                         
                   
                   
               }catch(NullPointerException ex){  
                   Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter all the reservation informations !");
                    alert.showAndWait();     
                   
               }
    
        
        
        
         }
        
    } 
    
    public void setLogin(String login){
        this.login=login;
    } 

    private void SetLoginAction(ActionEvent event) { 
        this.loginlabel.setText(login);
    }

    @FXML
    private void RoomsTableAction(MouseEvent event) { 
         
        Chambre reserv = TableRooms.getSelectionModel().getSelectedItem(); 
        
    }

    @FXML
    private void RefreshAction(ActionEvent event) {   
         oblist.clear();
         this.initialize(null, null);
         this.CheckInDate.setValue(null); 
        this.CheckOutDate.setValue(null); 
        
    }

    @FXML
    private void CancelReservationAction(ActionEvent event) throws SQLException {     
        Reservation a = Reservation_Table.getSelectionModel().getSelectedItem(); 
        
        if(a==null){  
                
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("");
                                alert.setHeaderText(null);
                                alert.setContentText("please select a reservation to cancel");
                                alert.showAndWait();  
            
        }else{  
            
                               if(Database.DeleteReservation(a)){  
                                   
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("");
                                alert.setHeaderText(null);
                                alert.setContentText("your reservetion has been deleted !");
                                alert.showAndWait();  
                                
                                this.initialize(null, null);
                                   
                               }
            
        }
        
    }

    @FXML
    private void UpdateRervationAction(ActionEvent event) throws SQLException {   
        
        
        
        
             try{ 
                         Date che1 = Date.valueOf(this.InDate.getValue()) ; 
                         Date che2 = Date.valueOf(this.OutDate.getValue());     
                         
                         Reservation a = Reservation_Table.getSelectionModel().getSelectedItem();
                         
                         if(Database.isRoomReserved(a.getID_Chambre(), che1, che2)){  
                             
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("");
                                alert.setHeaderText(null);
                                alert.setContentText("please this room isn't available for this date !");
                                alert.showAndWait();  
                             
                         }else{  
                             
                              if(Database.UpdateReservation(a.getId_Reservation(), che1, che2)){  
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("");
                                alert.setHeaderText(null);
                                alert.setContentText("your reservation has been updated successfully");
                                alert.showAndWait();    
                                this.YReservationAction(event);
                                
                                  
                              }
                         } 
                         
                         
                         
                         
                         
                         
                          
             }catch(NullPointerException ex){ 
                 
                 
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter all the reservation informations to update your reservation !");
                    alert.showAndWait();  
                 
             }
       
        
        
        
        
        
    }

    @FXML
    private void MyReservationTableAction(MouseEvent event) { 
        
        Reservation r = Reservation_Table.getSelectionModel().getSelectedItem() ;  
        
         InDate.setValue(r.getCheck_in().toLocalDate()); 
         
         OutDate.setValue(r.getCheck_out().toLocalDate()); 
         
        
        
    }

    @FXML
    private void RefreshMyReservation(ActionEvent event) { 
        InDate.setValue(null); 
        OutDate.setValue(null);
        
    }

    @FXML
    private void HotelInformationAction(ActionEvent event) { 
        
        this.HotelAddressPan.toFront(); 
        
    }

    @FXML
    private void CheckPriceAction(ActionEvent event) { 
        
               Chambre b =  this.TableRooms.getSelectionModel().getSelectedItem();   
               
               if(b == null){  
                   
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a room !");
                    alert.showAndWait();  
                   
               }else{
               
                   if(this.CheckInDate.getValue() == null || this.CheckOutDate == null){
                
                          
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter all the reservation informations !");
                    alert.showAndWait();  
                  
              
                   }else{  
                       
                          Date a1 = Date.valueOf(this.CheckInDate.getValue());  
                          Date a2 = Date.valueOf(this.CheckOutDate.getValue());  
                    
                    
                           
                   
                                long price =  Reservation.DateDifference(a1,a2);   
                                 
                                 Alert alert = new Alert(AlertType.INFORMATION);
                                 alert.setTitle("Total price");
                                 alert.setHeaderText(null);
                                 alert.setContentText("Total price : "+price*b.getPrix()+"DHS"); 
                                 alert.showAndWait();  
                       
                   }
              
               
               }
               
               
    }
   
}
