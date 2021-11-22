package chattecnologie;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadInstauraConnessione extends Thread {
    CGestioneChat gestoreChat;
    CGestorePacchetto gestorePacchetto;

    public ThreadInstauraConnessione(CGestioneChat gestoreChat, CGestorePacchetto gestorePacchetto) {
        this.gestoreChat = gestoreChat;
        this.gestorePacchetto = gestorePacchetto;
    }

    @Override
    public void run() {
        String risposta = "";
        try {
            gestorePacchetto.InvioPacchetto("c;"+gestoreChat.getNickname()+";", gestoreChat.getIndirizzoDestinatario());
        } catch (IOException ex) {
            Logger.getLogger(ThreadInstauraConnessione.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        try {
            risposta = gestorePacchetto.RicezionePacchetto();
        } catch (IOException ex) {
            Logger.getLogger(ThreadInstauraConnessione.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //SE RICEVO DA UN ALTRO ALLORA NO
            
        if(risposta.substring(0, 1).equals("y")){
            try {
                gestorePacchetto.InvioPacchetto("y;", gestoreChat.getIndirizzoDestinatario());
            } catch (IOException ex) {
                Logger.getLogger(ThreadInstauraConnessione.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            gestoreChat.setNicknameDestinatario(risposta.substring(2, risposta.length()));
                
            System.out.println("CONNESSIONE STABILITA");
            System.out.println("Premere Invio");
        } else if(risposta.substring(0, 1).equals("n")){
            System.out.println("CONNESSIONE NON STABILITA");
            System.out.println("Premere Invio");
        }
    }
}