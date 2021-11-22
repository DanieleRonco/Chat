package chattecnologie;

import java.net.SocketException;

public class ChatTecnologie {
    public static void main(String[] args) throws SocketException {
        CGestorePacchetto gestorePacchetto = new CGestorePacchetto();
        CGestioneChat Gestore = new CGestioneChat("danieleRonco", gestorePacchetto);
        ThreadInput ti1 = new ThreadInput(Gestore);
        
        ti1.start();
    }
}