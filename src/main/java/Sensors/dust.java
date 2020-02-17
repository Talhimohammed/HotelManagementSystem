/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class dust implements Runnable { 
    
    public  void run()
            
{ 
        
        try {
            String hote = "127.0.0.1" ;
            int port = 1000 ;
            Socket soc = new Socket (hote, port) ;
            OutputStream flux = soc.getOutputStream() ;
            OutputStreamWriter sortie = new OutputStreamWriter (flux) ;
            
            File file=new File("C:\\Users\\user\\Documents\\NetBeansProjects\\Hotel\\src\\main\\java\\Sensors\\dustsensor.txt");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line ;
            
            while((line=br.readLine())!=null)
            {
                
                sortie.write(line+"\n");
                
                sortie.flush();
                
                Thread.sleep(1000);
                
                    
            }       } catch (IOException ex) {
            Logger.getLogger(dust.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(dust.class.getName()).log(Level.SEVERE, null, ex);
        }



}
    
}
