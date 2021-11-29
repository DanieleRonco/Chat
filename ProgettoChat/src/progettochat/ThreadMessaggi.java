/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettochat;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC-Fisso
 */
public class ThreadMessaggi extends Thread {
    private CGestoreChat gestore;
    
    public ThreadMessaggi(CGestoreChat gestore){
        this.gestore = gestore;
    }

    @Override
    public void run() {
        int i=0;
        while(gestore.isConnessioneLibera() == false)
        {
            System.out.println("Aspetto un messaggio");
            String ritorno = gestore.CercaPacchettiComunicazione();
            if(ritorno != "") {
                System.out.println("Messaggio diverso da vuoto");
                gestore.frame.SetLabel(gestore.getNomeDestinatario() + " " + ritorno);
            }
            
            try {
                Thread.sleep(5000);
                
                /*
                i++;
                gestore.frame.SetLabel(i+"");
                try {
                sleep(1000);
                } catch (InterruptedException ex) {
                Logger.getLogger(ThreadMessaggi.class.getName()).log(Level.SEVERE, null, ex);
                }
                */
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadMessaggi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(gestore.CercaPacchettiChiusura()){
                try {
                    gestore.ChiudiConnessione();
                } catch (IOException ex) {
                    Logger.getLogger(ThreadMessaggi.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            
            try {
                gestore.EliminaPacchetti();
            } catch (IOException ex) {
                Logger.getLogger(ThreadMessaggi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        gestore.frame.SetLabel("");
    }
}