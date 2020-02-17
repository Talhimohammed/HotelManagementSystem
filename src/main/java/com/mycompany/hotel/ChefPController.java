/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotel;

import API.Database;
import Model.Chambre;
import Sensors.durt;

import Sensors.dust;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeInLeft;
import animatefx.animation.FadeInRight;
import com.jfoenix.controls.JFXButton;
import com.sun.scenario.DelayedRunnable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
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
public class ChefPController implements Initializable { 
    
    @FXML
    private Pane PanChart;
    @FXML
    private TableView<Chambre> tableroom;
    @FXML
    private TableColumn<Chambre,String> idchambre;
    @FXML
    private TableColumn<Chambre,String> capacity;
    @FXML
    private TableColumn<Chambre,String> type;

    /**
     * Initializes the controller class.
     */ 
    
     ObservableList<Chambre> oblist1 = FXCollections.observableArrayList(); 
    @FXML
    private JFXButton ChartPan;
    @FXML
    private JFXButton Refresh;
    @FXML
    private Pane RoomsPan;
    @FXML
    private ProgressIndicator ProgressBar;
    private Pane MenuPan;
    @FXML
    private Pane MenuRoomPool;
    @FXML
    private Label Label1;
    @FXML
    private Pane PoolPan;
    @FXML
    private JFXButton LogO;
    @FXML
    private Label lab;
    @FXML
    private Label labelpool;
    @Override 
    
     
 
    public void initialize(URL url, ResourceBundle rb) { 
        
        oblist1.clear();
        try {
            Database.getAllRooms(oblist1);
        } catch (SQLException ex) {
            Logger.getLogger(ChefPController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              idchambre.setCellValueFactory(new PropertyValueFactory<>("ID_Chambre"));
              capacity.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
              type.setCellValueFactory(new PropertyValueFactory<>("Type"));
              
              tableroom.setItems(oblist1);
        
        
        
              ProgressBar.setStyle("-fx-accent :  #3C9AB7;");              
           
           
        
       
         
        
        
      
    }    

    @FXML
    private void tochart(ActionEvent event) {   
        
        this.PanChart.toFront();    
                   
        
    }   
    
    
 
    
     
        

    @FXML
   private void RefreshAction(ActionEvent event) { 
    //   this.initialize(null, null);
    } 

    private void Back(ActionEvent event) { 
        this.RoomsPan.toFront();
    }
    
   
  
    

    @FXML
    private void RoomsAction(ActionEvent event) { 
        new FadeInRight(RoomsPan).play();   
        
       
        
         
         RoomsPan.toFront();    
          int port=1000;
              try {
                  ServerSocket sersoc = new ServerSocket (port) ;  
                   System.out.println ("serveur active sur port " + port) ;  
                   
                  ScheduledExecutorService scheduledExecutorService;
                  scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();    
                  
                  dust a = new dust();  
                  Thread t = new Thread(a);
                  t.start();
                  
                  Socket soc = sersoc.accept();
                  InputStream flux = soc.getInputStream ();
                 BufferedReader entree = new BufferedReader (new InputStreamReader (flux)) ;
                   
                   scheduledExecutorService.scheduleAtFixedRate(() -> {
                      
                      
                      Platform.runLater(() -> {
                          try {
                              String st = entree.readLine();
                              if (st == null ) {
                                  
                                  scheduledExecutorService.shutdown();
                                  
                              }else { 
                                  
                                  
                                  double z =  Double.valueOf(st);  
                                  
                                  if(z == 0.4){  
                                     lab.setText("this room must be cleaned"); 
                                    ProgressBar.setStyle("-fx-accent : red;");
                                      
                                  }
                                
                                  this.ProgressBar.setProgress(z);  
                                  
                                 
                                  
                                  
                              }
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                      });
                      
                  }, 0,2, TimeUnit.SECONDS);
                   
                   
                  
                  
                  
                  
                  
                  
                  
                  
              } catch (IOException ex) {
                  Logger.getLogger(ChefPController.class.getName()).log(Level.SEVERE, null, ex);
              } 
              
          
         
         
         
         
         
       
         
    }

    @FXML
    private void PoolAction(ActionEvent event) {  
        
         new FadeInRight(this.PoolPan).play();  
         
         PoolPan.toFront();   
          
            final CategoryAxis xAxis = new CategoryAxis();
              final NumberAxis yAxis = new NumberAxis();
              xAxis.setLabel("Time/s");
              xAxis.setAnimated(true);
              yAxis.setLabel("dirt Percentage (%)");
              yAxis.setAnimated(true);
              final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
              lineChart.setTitle("Quality of Pool");
              lineChart.setAnimated(false);
              XYChart.Series<String, Number> series = new XYChart.Series<>();
              series.setName("Data Series");
              lineChart.getData().add(series);
              PoolPan.getChildren().add(lineChart);
              final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
             
              int port = 3000 ;
              ServerSocket sersoc ;
              try {
                  sersoc = new ServerSocket (port); 
                  
                  
                   ScheduledExecutorService scheduledExecutorService2;
                   scheduledExecutorService2 = Executors.newSingleThreadScheduledExecutor(); 
                   
                  durt a = new durt();  
                  Thread t = new Thread(a);
                  t.start();
                 
                  Socket soc = sersoc.accept();
                  InputStream flux = soc.getInputStream ();
                  BufferedReader entree = new BufferedReader (new InputStreamReader (flux)) ;  
                  System.out.println ("serveur active sur port " + port) ;
                  
                  
                  scheduledExecutorService2.scheduleAtFixedRate(() -> {
                      
                      Integer random = ThreadLocalRandom.current().nextInt(10);
                      
                      
                      Platform.runLater(() -> {
                          
                          Date now = new Date();
                          
                          
                          try {
                              String st = entree.readLine();
                              if (st == null ) {
                                  
                                  scheduledExecutorService2.shutdown();
                                  
                              }else { 
                                  
                                  if(Integer.parseInt(st)== 35){  
                                      
                                      labelpool.setText("Swimming pool water must be recycled");
                                      
                                  }
                                  
                                  series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now),Integer.parseInt(st))); 
                                  
                                  
                              }
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                          
                      });
                  }, 0, 1, TimeUnit.SECONDS);
                  
                  
                  
                  
              } catch (IOException ex) {
                Logger.getLogger(ChefPController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
         
         
         
        
    }

    @FXML
    private void LogOutAction(ActionEvent event) throws IOException {   
        
          Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
                            Scene scene = new Scene(root);
                            scene.getStylesheets().add("/styles/Styles.css");     
                            
                            //close login interface 
                            Stage stage1 = (Stage)LogO.getScene().getWindow();  
                            stage1.close();
                            
                            //display Admin interface
                            Stage stage = new Stage();
                     
                            stage.setScene(scene);
                            stage.show();    
                            
        
        
        
    }

    @FXML
    private void BackToAction(ActionEvent event) {
        new FadeInRight(this.MenuRoomPool).play();  
         
         MenuRoomPool.toFront(); 
    }

    @FXML
    private void BackAction(ActionEvent event) {  
        
               new FadeInRight(RoomsPan).play();  
         
               RoomsPan.toFront(); 

        
        
    }

     

    

    
}
