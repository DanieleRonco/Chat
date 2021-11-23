package progettochat;

import java.net.SocketException;

public class ProgettoChat {
    public static void main(String[] args) throws SocketException {
        //Gestore della chat
        CGestoreChat GestoreChat = new CGestoreChat();
        
        //Thread per l'ascolto
        ThreadAscolto TA1 = new ThreadAscolto(GestoreChat, 2003);
        
        //Si avvia il thread per l'ascolto
        TA1.start();
    }  
}