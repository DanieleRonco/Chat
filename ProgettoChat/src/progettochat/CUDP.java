package progettochat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class CUDP {
    DatagramSocket gestore;
    
    public CUDP() throws SocketException {
        this.gestore = new DatagramSocket(2002);
    }
    
    public void InvioPacchetto(String messaggio, InetAddress indirizzo) throws IOException{
        byte[] bufferRisposta = messaggio.getBytes();
        DatagramPacket pacchettoRisposta = new DatagramPacket(bufferRisposta, bufferRisposta.length);
        pacchettoRisposta.setAddress(indirizzo);
        pacchettoRisposta.setPort(2003); //DA CAMBIARE CON 2003
        gestore.send(pacchettoRisposta);
    }
    
    public String RicezionePacchetto() throws IOException{
        byte[] bufferRicevuto = new byte[1500];
        DatagramPacket pacchettoRicevuto = new DatagramPacket(bufferRicevuto, bufferRicevuto.length);
        gestore.receive(pacchettoRicevuto);
        byte[] bufferDatiRicevuto = pacchettoRicevuto.getData();
        String datiStringa = new String(Arrays.copyOfRange(bufferDatiRicevuto, 0, pacchettoRicevuto.getLength()));
        return datiStringa;
    }
}