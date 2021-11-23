package chattecnologie;

import java.net.SocketException;

public class ChatTecnologie {
    public static void main(String[] args) throws SocketException {
        //Classe per la gestione del pacchetto UDP
        CGestorePacchetto gestoreUDP = new CGestorePacchetto();
        
        //Classe per la gestione della funzionalità della Chat
        CGestioneChat gestoreChat = new CGestioneChat("danieleRonco", gestoreUDP);
        
        //Thread per l'input dei comandi
        ThreadInput ti1 = new ThreadInput(gestoreChat);
        
        //Si avvia il thread per l'input dei comandi
        ti1.start();
    }
}