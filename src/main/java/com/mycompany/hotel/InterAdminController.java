/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel;

import API.Database;
import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InterAdminController implements Initializable {
    @FXML
    private JFXTextField FN;
    @FXML
    private JFXTextField Login;
    @FXML
    private JFXTextField Passw;
    @FXML
    private JFXComboBox<String> Type;
    @FXML
    private JFXTextField Index;
    @FXML
    private TableView<User> Table_User;
    @FXML
    private TableColumn<User,String> Name_Col;
    @FXML
    private TableColumn<User,String> Login_Col;
    @FXML
    private TableColumn<User,String> Type_Col;
    @FXML
    private TableColumn<User,String> Password_Col;
    @FXML
    private TableColumn<User,String> Index_Col;

    /**
     * Initializes the controller class.
     */ 
    
    ObservableList<User> oblist = FXCollections.observableArrayList();  
    @FXML
    private JFXButton Button_Add;
    @FXML
    private JFXButton Update_Button;
    @FXML
    private JFXButton Button_Delete;
    @FXML
    private JFXButton LogOutButton;
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Type.getItems().removeAll(Type.getItems());
        Type.getItems().addAll("Admin", "Client", "Receptionist","Cleaning chief","Property agent");
        Type.getSelectionModel().select("Admin");    
        
         oblist.clear();
        
        try { 
            Connection conn = Database.getConnection();  
            ResultSet rs = conn.createStatement().executeQuery("select * from utilisateur"); 
            
            while(rs.next()){ 
                oblist.add(new User(rs.getString("login"),rs.getString("password"),rs.getString("indice"),rs.getString("fullName"),rs.getString("type")));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(InterAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        Name_Col.setCellValueFactory(new PropertyValueFactory<User,String>("fullName"));  
        Login_Col.setCellValueFactory(new PropertyValueFactory<User,String>("login"));  
        Type_Col.setCellValueFactory(new PropertyValueFactory<User,String>("type"));  
        Password_Col.setCellValueFactory(new PropertyValueFactory<User,String>("password"));  
        Index_Col.setCellValueFactory(new PropertyValueFactory<User,String>("indice"));  
        
        
        Table_User.setItems(oblist); 
        
        
        
        
        
    }    

    @FXML
    private void AddAction(ActionEvent event) throws SQLException {  
         String log = Login.getText(); 
         String pas = Passw.getText(); 
         String FullName = FN.getText(); 
         String Type_1 = Type.getSelectionModel().getSelectedItem().toString(); 
         String ind = Index.getText(); 
         
         
         User u1 = new User(log,pas,ind,FullName,Type_1);  
         
         if(Database.AddUser(u1)){  
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("User has been created successfully");
                    alert.showAndWait();  
                    
                    this.initialize(null, null); 
                   
         }else{ 
             
         }
                 
    }

    @FXML
    private void UpdateAction(ActionEvent event) throws SQLException { 
        
        User a =  Table_User.getSelectionModel().getSelectedItem();  
        
        if(a == null){ 
            
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select an user ");
                    alert.showAndWait();  
            
        }else{  
                       
                 if(Database.UpdateUser(a,new User(Login.getText(),Passw.getText(),Index.getText(),FN.getText(),Type.getSelectionModel().getSelectedItem().toString()))){ 
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("User has been Updated successfully");
                    alert.showAndWait();  
                    
                    this.initialize(null, null); 
                 }
        
    }
        
    } 

    @FXML
    private void DeleteAction(ActionEvent event) throws SQLException {  
        
       User a =  Table_User.getSelectionModel().getSelectedItem();   
       
       if(a == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select an user ");
                    alert.showAndWait();  
       }else{
       
       if(Database.DeleteUser(a)){
            
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("User has been deleted successfully");
                    alert.showAndWait();  
                    
                    this.initialize(null, null); 
           
       }else{ 
           
           
       }
         
       }
                        
         
    }

    @FXML
    private void TableAction(MouseEvent event) { 
        
         User a =  Table_User.getSelectionModel().getSelectedItem();    
         
         Login.setText(a.getLogin()); 
         FN.setText(a.getFullName()); 
         Passw.setText(a.getPassword()); 
         Index.setText(a.getIndice());  
          Type.getSelectionModel().select(a.getType());    
         
        
    }

    @FXML
    private void RefreshAction(ActionEvent event)  { 
        
            this.initialize(null, null);  
            this.FN.setText(null); 
            this.Index.setText(null); 
            this.Login.setText(null); 
            this.Passw.setText(null);
          
    }

    @FXML
    private void LogOutAction(ActionEvent event) throws IOException { 
        
         Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
                            Scene scene = new Scene(root);
                            scene.getStylesheets().add("/styles/Styles.css");     
                            
                            //close login interface 
                            Stage stage1 = (Stage)LogOutButton.getScene().getWindow();  
                            stage1.close();
                            
                            //display Admin interface
                            Stage stage = new Stage();
                     
                            stage.setScene(scene);
                            stage.show();   
    }
    
}
