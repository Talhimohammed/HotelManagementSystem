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
public class client2 { 
     
     private int idc ; 
     private String cin ; 
     private String email ;  
     private String phone ; 
      
      public client2(int idc,String cin,String email,String phone){  
          
          this.idc = idc ; 
          this.cin = cin ; 
          this.email = email ; 
          this.phone = phone ; 
          
      }

    /**
     * @return the idc
     */
    public int getIdc() {
        return idc;
    }

    /**
     * @param idc the idc to set
     */
    public void setIdc(int idc) {
        this.idc = idc;
    }

    /**
     * @return the cin
     */
    public String getCin() {
        return cin;
    }

    /**
     * @param cin the cin to set
     */
    public void setCin(String cin) {
        this.cin = cin;
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
