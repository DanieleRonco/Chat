/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ascolto;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author PC-Fisso
 */
public class Ascolto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, IOException {
        // TODO code application logic here
        
        DatagramSocket gestore = new DatagramSocket(2004);
    
    
    
        /*byte[] bufferRisposta = messaggio.getBytes();
        DatagramPacket pacchettoRisposta = new DatagramPacket(bufferRisposta, bufferRisposta.length);
        pacchettoRisposta.setAddress(indirizzo);
        pacchettoRisposta.setPort(2003);
        gestore.send(pacchettoRisposta);*/
    
    
    
        byte[] bufferRicevuto = new byte[1500];
        DatagramPacket pacchettoRicevuto = new DatagramPacket(bufferRicevuto, bufferRicevuto.length);
        gestore.receive(pacchettoRicevuto);
        byte[] bufferDatiRicevuto = pacchettoRicevuto.getData();
        String datiStringa = new String(Arrays.copyOfRange(bufferDatiRicevuto, 0, pacchettoRicevuto.getLength()));
        System.out.println(datiStringa);
        
        Scanner jin = new Scanner(System.in);
        String messaggio = jin.nextLine();
        
        byte[] bufferRisposta = messaggio.getBytes();
        DatagramPacket pacchettoRisposta = new DatagramPacket(bufferRisposta, bufferRisposta.length);
        pacchettoRisposta.setAddress(pacchettoRicevuto.getAddress());
        pacchettoRisposta.setPort(2003);
        gestore.send(pacchettoRisposta);
    
    }
}