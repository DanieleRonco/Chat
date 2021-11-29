package progettochat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadInstauraConnessione extends Thread {
    private CGestoreChat gestore;
    private CPacchetto pacchettoTemp;

    public ThreadInstauraConnessione() {
        this.gestore = null;
        this.pacchettoTemp = null;
    }
    
    public ThreadInstauraConnessione(CGestoreChat gestore) {
        this.gestore = gestore;
        this.pacchettoTemp = null;
    }

    @Override
    public void run() {
        pacchettoTemp = gestore.IsPresenteInListaPacchettiConnessione(gestore.getIndirizzoStringa());
        
        if(pacchettoTemp == null){
            try {
                gestore.getGestoreUDP().InvioPacchetto("c;" + gestore.getMioNome() + ";", gestore.getIndirizzoDestinatario());
            } catch (IOException ex) {
                Logger.getLogger(ThreadInstauraConnessione.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            while(pacchettoTemp == null && !gestore.isTerminaTentativoConnessione()){
                pacchettoTemp = null;
                pacchettoTemp = gestore.IsPresenteInListaPacchettiConnessione(gestore.getIndirizzoStringa());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadInstauraConnessione.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Aspetto la risposta"); 
            }
                
            if(gestore.isTerminaTentativoConnessione() == false){
                if(pacchettoTemp.getOperazione().equals("y")){
                    gestore.setNomeDestinatario(pacchettoTemp.getMessaggio());
                    InviaY();
                }
            }
        } else {
            InviaY();
        }
        
        
        ThreadMessaggi tm1 = new ThreadMessaggi(gestore);
        tm1.start();
    }
    
    public void InviaY(){
        try {
            gestore.getGestoreUDP().InvioPacchetto("y;", gestore.getIndirizzoDestinatario());
        } catch (IOException ex) {
            Logger.getLogger(ThreadInstauraConnessione.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gestore.setConnessioneLibera(false);
        gestore.setNomeDestinatario(pacchettoTemp.getMessaggio());
        
        gestore.setNomeDestinatario(pacchettoTemp.getMessaggio());
        
        System.out.println("CONNESSIONE INSTAURATA");
    }
}