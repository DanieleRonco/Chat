package progettochat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadAscolto extends Thread {
    private DatagramSocket ascolto;
    private CGestoreChat gestore;
    
    public ThreadAscolto() throws SocketException {
        this.ascolto = new DatagramSocket(2003);
        this.gestore = new CGestoreChat();
    }

    public ThreadAscolto(CGestoreChat gestore, int porta) throws SocketException {
        this.ascolto = new DatagramSocket(porta);
        this.gestore = gestore;
    }
    
    @Override
    public void run() {
        System.out.println("INIZIO L'ASCOLTO");
        while(true){
            byte[] bufferRicevuto = new byte[1500];
            DatagramPacket pacchettoRicevuto = new DatagramPacket(bufferRicevuto, bufferRicevuto.length);
            try {
                ascolto.receive(pacchettoRicevuto);
            } catch (IOException ex) {
                Logger.getLogger(ThreadAscolto.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] bufferDatiRicevuto = pacchettoRicevuto.getData();
            
            String datiStringa = pacchettoRicevuto.getAddress().toString() + ";";
            datiStringa += new String(Arrays.copyOfRange(bufferDatiRicevuto, 0, pacchettoRicevuto.getLength()));
            
            gestore.InserisciPacchetto(datiStringa);
            
            System.out.println("Arrivato pacchetto " + datiStringa);
        }
    }
}