/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author user
 */
public class Chambre { 
    
     private int ID_Chambre ; 
     private String Type ;  
     private int Capacity ; 
  //   private int Dirt_Percentage ; 
  //   private boolean Availability ;  
     private int prix ; 
     
               public Chambre(int ID_Chambre,String Type ,int Capacity,int prix){  
                    
                    this.ID_Chambre=ID_Chambre ; 
                    this.Type=Type ; 
                    this.Capacity=Capacity; 
                   
                    this.prix = prix ; 
                   
               }

    /**
     * @return the ID_Chambre
     */
    public int getID_Chambre() {
        return ID_Chambre;
    }

    /**
     * @param ID_Chambre the ID_Chambre to set
     */
    public void setID_Chambre(int ID_Chambre) {
        this.ID_Chambre = ID_Chambre;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return the Capacity
     */
    public int getCapacity() {
        return Capacity;
    }

    /**
     * @param Capacity the Capacity to set
     */
    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    /**
     * @return the Dirt_Percentage
     */
  
   
    public int getPrix(){ 
        return this.prix;
    } 
    public void setPrix(int prix){ 
        this.prix = prix ; 
    }
     
    
}
