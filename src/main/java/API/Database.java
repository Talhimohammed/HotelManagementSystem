/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import Model.Chambre;
import Model.Reservation;
import Model.User;
import Model.client;
import Model.client2;
import com.mysql.fabric.xmlrpc.Client;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author user
 */
public class Database { 
           
      public static Connection getConnection()throws SQLException{  
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelbd","root","");   
            
            
            return con ; 
      } 
      
      public static boolean AddUser(User a) throws SQLException{ 
           
            Connection con = Database.getConnection(); 
         PreparedStatement statement = con.prepareStatement("INSERT INTO utilisateur (login,password,indice,type,fullName) VALUES (?,?,?,?,?)"); 
         statement.setString(1,a.getLogin());  
         statement.setString(2,a.getPassword());  
         statement.setString(3,a.getIndice()); 
         statement.setString(4,a.getType()); 
         statement.setString(5,a.getFullName());  
     
         int rs = statement.executeUpdate();  
             if(rs != 0){ 
                   return true ; 
                    
             }else{ 
                  return false ; 
             }
          
      } 
      
      
      public static boolean DeleteUser(User a ) throws SQLException { 
          Connection con = Database.getConnection();  
          PreparedStatement statement = con.prepareStatement("delete from utilisateur where login = ? AND password = ?");   
          statement.setString(1,a.getLogin());  
          statement.setString(2,a.getPassword());   
          
          
         int rs = statement.executeUpdate();  
             if(rs != 0){ 
                   return true ; 
                    
             }else{ 
                  return false ; 
             }
           
          
      } 
      
      public static boolean UpdateUser(User a , User n) throws SQLException{  
            
          Connection con = Database.getConnection();  
          PreparedStatement statement = con.prepareStatement("Update utilisateur set login = ? , password = ? , indice = ? , type = ? , fullName = ? where login = ? AND password = ? ");   
          statement.setString(1,n.getLogin());  
          statement.setString(2,n.getPassword());
          statement.setString(3,n.getIndice()); 
          statement.setString(4,n.getType()); 
          statement.setString(5,n.getFullName());   
          statement.setString(6,a.getLogin());  
          statement.setString(7,a.getPassword());   
          
             int rs = statement.executeUpdate();  
             if(rs != 0){ 
                  return true ; 
                    
             }else{ 
                  return false ; 
             }
           
          
             
             
      } 
      
      public static boolean AddClient(client c) throws SQLException{    
          
         Random rnd = new Random();
         int id = 100000 + rnd.nextInt(900000);
            
         Connection con = Database.getConnection(); 
         PreparedStatement statement = con.prepareStatement("INSERT INTO client (id_client,login,cinp,email,phone) VALUES (?,?,?,?,?)"); 
         statement.setString(1,""+id+"");  
         statement.setString(2,c.getLogin());  
         statement.setString(3,c.getCinp()); 
         statement.setString(4,c.getEmail()); 
         statement.setString(5,c.getPhone());  
     
         int rs = statement.executeUpdate();  
             if(rs != 0){ 
                   return true ; 
                    
             }else{ 
                  return false ; 
             }
          
          
      }  
      
      public static void getAllRooms(ObservableList<Chambre> obs) throws SQLException{  
           Connection con = Database.getConnection();   
           ResultSet rs = con.createStatement().executeQuery("select * from chambre"); 
            
            while(rs.next()){ 
                obs.add(new Chambre(rs.getInt("id_chambre"),rs.getString("type"),rs.getInt("capacity"),rs.getInt("cost")));
            }  
            
   
      } 
      
      public static boolean AddReservation(Reservation r) throws SQLException{ 
          Connection con = Database.getConnection();   
          
          PreparedStatement statement = con.prepareStatement("INSERT INTO reservation (id_Reservation,check_in,check_out,id_client,id_chambre) VALUES (?,?,?,?,?)");   
          statement.setInt(1,r.getId_Reservation()); 
          statement.setDate(2,r.getCheck_in()); 
          statement.setDate(3,r.getCheck_out()); 
          statement.setInt(4, r.getID_Client()); 
          statement.setInt(5,r.getID_Chambre());  
          
          int rs = statement.executeUpdate();  
             if(rs != 0){ 
                   return true ; 
                    
             }else{ 
                  return false ; 
             }
      
      } 
      
      public static int getIDclient(String a) throws SQLException{  
           Connection con = Database.getConnection(); 
           ResultSet rs = con.createStatement().executeQuery("select id_client from client where login ="+"'"+a+"'");   
           int e = 0 ; 
           if(rs.next()){ 
              e =  rs.getInt("id_client");
           } 
           
           return e ; 
           
            
      } 
      
      
 
       // get all the clients reservation 
      public static void getReservationsByClient(int Id ,ObservableList<Reservation> obs) throws SQLException{    
          
             Connection con = Database.getConnection();   
           ResultSet rs = con.createStatement().executeQuery("select * from reservation where id_client = "+"'"+Id+"'"); 
            
            while(rs.next()){ 
                                    
                 obs.add(new Reservation(rs.getInt("id_Reservation"),rs.getDate("check_in"),rs.getDate("check_out"),rs.getInt("id_client"),rs.getInt("id_chambre")));
                
                
            }  
          
          
          
      } 
      
      public static boolean isRoomReserved(int id_room,Date in,Date out) throws SQLException{   // you should modify
          
          Connection con = Database.getConnection();    
          ResultSet rs = con.createStatement().executeQuery("select * from reservation where id_chambre = "+"'"+id_room+"'"+"AND check_in ='"+in+"' OR check_out ='"+out+"'");  
          if(rs.next()){ 
              return true ; 
          }else{ 
              return false ; 
          } 
     
          
          
      } 
      public static boolean UpdateReservation( int id_reservation,Date NewDateIn , Date NewDateOut) throws SQLException{    
          
                    
          Connection con = Database.getConnection();  
          PreparedStatement statement = con.prepareStatement("Update Reservation set check_in = ?,check_out = ? where id_Reservation = ?");   
          statement.setDate(1,NewDateIn);   
          statement.setDate(2,NewDateOut);   
          statement.setInt(3,id_reservation);    
           
             int rs = statement.executeUpdate();  
             if(rs != 0){ 
                  return true ; 
                    
             }else{ 
                  return false ; 
             }
           
          
      } 
      
      public static boolean DeleteReservation(Reservation r) throws SQLException{  
          
             Connection con = Database.getConnection();  
          PreparedStatement statement = con.prepareStatement("delete from reservation where id_Reservation = ?");   
          statement.setInt(1,r.getId_Reservation());
          
         int rs = statement.executeUpdate();  
             if(rs != 0){ 
                   return true ; 
                    
             }else{ 
                  return false ; 
             }
          
      } 
      
      public static void GetAllReservation(ObservableList<Reservation> obs) throws SQLException{ 
          
           Connection con = Database.getConnection();   
           ResultSet rs = con.createStatement().executeQuery("select * from reservation"); 
            
            while(rs.next()){ 
                                    
                 obs.add(new Reservation(rs.getInt("id_Reservation") ,rs.getDate("check_in"),rs.getDate("check_out"),rs.getInt("id_client"),rs.getInt("id_chambre")));
                
                
            }  
           
      } 
      
      public static int GetNumberOfReservations() throws SQLException{  
          Connection con = Database.getConnection();     
          int a = 0 ;  
          ResultSet rs = con.createStatement().executeQuery("select COUNT(*) from reservation");  
          
          if(rs.next()){ 
              a = rs.getInt(1);
          } 
          
          return a ; 
          
      }  
      
      public static boolean AddRoom(Chambre a) throws SQLException{ 
          
             Connection con = Database.getConnection();   
          
          PreparedStatement statement = con.prepareStatement("INSERT INTO chambre (id_chambre,capacity,type,cost) VALUES (?,?,?,?)");   
          statement.setInt(1,a.getID_Chambre()); 
          statement.setInt(2,a.getCapacity()); 
          statement.setString(3,a.getType()); 
          statement.setInt(4,a.getPrix()); 
       
          
          int rs = statement.executeUpdate();  
             if(rs != 0){ 
                   return true ; 
                    
             }else{ 
                  return false ; 
             }
          
          
      } 
      
      public static int getNumberRooms() throws SQLException{  
          
           Connection con = Database.getConnection();     
          int a = 0 ;  
          ResultSet rs = con.createStatement().executeQuery("select COUNT(*) from chambre");  
          
          if(rs.next()){ 
              a = rs.getInt(1);
          } 
          
          return a ; 
           
          
      } 
      
      public static boolean DeleteRoom(Chambre a) throws SQLException{ 
          Connection con = Database.getConnection();  
          PreparedStatement statement = con.prepareStatement("delete from Chambre where id_chambre = ?");   
          statement.setInt(1,a.getID_Chambre());
          
         int rs = statement.executeUpdate();  
             if(rs != 0){ 
                   return true ; 
                    
             }else{ 
                  return false ; 
             }
          
          
          
      } 
      
      public static boolean IStheRoomReserved(Chambre a) throws SQLException{     
          
          Connection con = Database.getConnection();  
          ResultSet rs = con.createStatement().executeQuery("select * from reservation where id_chambre = '"+a.getID_Chambre()+"'");  
          if(rs.next()){  
              
                            return true ; 
          }else{ 
                            return false ; 
              
          }
          
          
          
          
       } 
      
        public static boolean UpdateRoom(Chambre Old_Room, Chambre New_Room) throws SQLException{   
          
          Connection con = Database.getConnection();  
          PreparedStatement statement = con.prepareStatement("Update chambre set capacity = ? , type = ? , cost = ? where id_chambre = ?");   
          statement.setInt(1,New_Room.getCapacity()); 
          statement.setString(2,New_Room.getType()); 
          statement.setInt(3,New_Room.getPrix()); 
          statement.setInt(4,Old_Room.getID_Chambre());
         
           
             int rs = statement.executeUpdate();   
             
             if(rs != 0){  
                 
                  return true ;  
                  
             }else{  
                 
                  return false ;  
                  
             }
           
        } 
        
       /*   public static void GetAllClients(ObservableList<Reservation> obs) throws SQLException{ 
          
           Connection con = Database.getConnection();   
           ResultSet rs = con.createStatement().executeQuery("select * from client"); 
            
            while(rs.next()){ 
                                    
                 obs.add(new client()));
                
                
            }  
           
      } */
      
        
      
      
      
      
            public static void GetAllClients(ObservableList<client2> obs) throws SQLException{ 
          
           Connection con = Database.getConnection();   
           ResultSet rs = con.createStatement().executeQuery("select * from client"); 
            
            while(rs.next()){ 
                                    
                 obs.add(new client2(rs.getInt("id_client"),rs.getString("cinp"),rs.getString("email"),rs.getString("phone")));
                
                
            }  
           
      }  
            
         
          
          
          
      
      
      
      
}
