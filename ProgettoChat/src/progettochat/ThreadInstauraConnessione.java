package progettochat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadInstauraConnessione extends Thread {
    private CGestoreChat gestore;

    public ThreadInstauraConnessione() {
        this.gestore = null;
    }
    
    public ThreadInstauraConnessione(CGestoreChat gestore) {
        this.gestore = gestore;
    }

    @Override
    public void run() {
        if(gestore.IsPresenteInListaPacchettiConnessione(gestore.getIndirizzoStringa()) == null){
            try {
                //Non mi è arrivato un pacchetto da lui, allora gli invio "c;NICKNAME"
                System.out.println("Non è arrivato un pacchetto, allora lo invio io");
                gestore.getGestoreUDP().InvioPacchetto("c;NICKNAME;", gestore.getIndirizzo());
            } catch (IOException ex) {
                Logger.getLogger(ThreadInstauraConnessione.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Aspetto finchè non c'è y del peer 2
            while(!gestore.isTerminaTentativoConnessione()){
                System.out.println("Aspetto la risposta");
                
                CPacchetto pacchettoTemp = null;
                do{
                    pacchettoTemp = gestore.IsPresenteInListaPacchettiConnessione(gestore.getIndirizzoStringa());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThreadInstauraConnessione.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } while(pacchettoTemp == null);
                
                if(pacchettoTemp.getOperazione().equals("y")){
                    System.out.println("Vuole stabilire la connessione");
                    gestore.setConnessioneLibera(false);
                    InviaY();
                    
                    break;
                } else {
                    System.out.println("Non vuole stabilire la connessione");
                    break;
                }
            }
        } else {
            InviaY();
        }
        
        ThreadRifiutaConnessione trc1 = new ThreadRifiutaConnessione(this.gestore);
        trc1.start();
    }
    
    public void InviaY(){
        try {
            gestore.getGestoreUDP().InvioPacchetto("y;", gestore.getIndirizzo());
        } catch (IOException ex) {
            Logger.getLogger(ThreadInstauraConnessione.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("CONNESSIONE STABILITA");
    }
}