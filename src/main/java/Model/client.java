/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author user
 */
public class client extends User { 
     

     
     private String cinp ; 
     private String email ;   
     private String phone ; 

    public client(String login, String password, String indice, String fullName, String type,String cinp,String email,String phone) {
        super(login, password, indice, fullName, type); 
    
        this.cinp=cinp      ; 
        this.email=email    ; 
        this.phone=phone    ; 
        
    }   
    
    
   
    
   
    /**
     * @return the idClient
     */
  

    /**
     * @param idClient the idClient to set
     */
    
    /**
     * @return the cinp
     */
    public String getCinp() {
        return cinp;
    }

    /**
     * @param cinp the cinp to set
     */
    public void setCinp(String cinp) {
        this.cinp = cinp;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
     
    
}
