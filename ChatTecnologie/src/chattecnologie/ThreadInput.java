package chattecnologie;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadInput extends Thread {
    CGestioneChat gestore;
    Scanner jin;
    
    public ThreadInput(CGestioneChat gestore) {
        this.gestore = gestore;
        this.jin = new Scanner(System.in);
    }

    @Override
    public void run() {
        String input = "";
        while(true){
            input = jin.nextLine();
            
            if(input.equals("c")){
                //Si vuole instaurare una connessione
                System.out.println("Indicare l'indirizzo del destinatario");
                String indirizzo = jin.next();
                try {
                    gestore.InstauraConnessione(indirizzo);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ThreadInput.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadInput.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (input.equals("e")){
                //Si vuole terminare una connessione
            } else {
                //Si vuole inviare un messaggio
            }
        }
    }
}