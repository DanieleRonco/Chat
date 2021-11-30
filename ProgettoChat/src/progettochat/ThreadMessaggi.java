package progettochat;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadMessaggi extends Thread {
    private CGestoreChat gestore;
    
    public ThreadMessaggi(CGestoreChat gestore){
        this.gestore = gestore;
    }

    @Override
    public void run() {
        while(gestore.isConnessioneLibera() == false)
        {
            String ritorno = gestore.CercaPacchettiComunicazione();
            if(ritorno != "") {
                gestore.frameGrafica.SetLabel(gestore.getNomeDestinatario() + ":   " + ritorno);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadMessaggi.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(gestore.CercaPacchettiChiusura()){
                try {
                    gestore.ChiudiConnessione();
                } catch (IOException ex) {
                    Logger.getLogger(ThreadMessaggi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            /*
            try {
                gestore.EliminaPacchetti();
            } catch (IOException ex) {
                Logger.getLogger(ThreadMessaggi.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
        }
        gestore.frameGrafica.SetLabel("");
    }
}