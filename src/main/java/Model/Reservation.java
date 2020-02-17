/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author user
 */
public class Reservation { 
        private int Id_Reservation ;
        private Date check_in ;  
        private Date check_out ;  
        private int ID_Client ; 
        private int ID_Chambre ;   
        
          public Reservation(int Id_Reservation,Date check_in,Date check_out,int ID_Client,int ID_Chambre){   
              
               this.ID_Chambre= ID_Chambre ; 
               this.Id_Reservation= Id_Reservation ; 
               this.check_in=check_in ; 
               this.check_out=check_out; 
               this.ID_Client= ID_Client ; 
              
          }

    /**
     * @return the Id_Reservation
     */
    public int getId_Reservation() {
        return Id_Reservation;
    }

    /**
     * @param Id_Reservation the Id_Reservation to set
     */
    public void setId_Reservation(int Id_Reservation) {
        this.Id_Reservation = Id_Reservation;
    }

    /**
     * @return the check_in
     */
    public Date getCheck_in() {
        return check_in;
    }

    /**
     * @param check_in the check_in to set
     */
    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    /**
     * @return the check_out
     */
    public Date getCheck_out() {
        return check_out;
    }

    /**
     * @param check_out the check_out to set
     */
    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }

    /**
     * @return the ID_Client
     */
    public int getID_Client() {
        return ID_Client;
    }

    /**
     * @param ID_Client the ID_Client to set
     */
    public void setID_Client(int ID_Client) {
        this.ID_Client = ID_Client;
    }   
    
    public int getID_Chambre(){ 
        return this.ID_Chambre;
    } 
    
    public void setID_Chambre(int ID_Chambre){ 
        this.ID_Chambre=ID_Chambre ;
    }
    
     public static long DateDifference(Date firstDate , Date secondDate){ 
         
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS); 
        
        return diff ;  
        
    } 
     
        
        
}
