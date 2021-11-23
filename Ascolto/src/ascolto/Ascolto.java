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
    
    
        while(true){
            Scanner jin = new Scanner(System.in);
            String scelta = jin.next();
            
            if(scelta.equals("r")){
                byte[] bufferRicevuto = new byte[1500];
                DatagramPacket pacchettoRicevuto = new DatagramPacket(bufferRicevuto, bufferRicevuto.length);
                gestore.receive(pacchettoRicevuto);
                byte[] bufferDatiRicevuto = pacchettoRicevuto.getData();
                String datiStringa = new String(Arrays.copyOfRange(bufferDatiRicevuto, 0, pacchettoRicevuto.getLength()));
                System.out.println(datiStringa);
            } else if (scelta.equals("i")){
                Scanner jin2 = new Scanner(System.in);
                String messaggio = jin2.nextLine();

                byte[] bufferRisposta = messaggio.getBytes();
                DatagramPacket pacchettoRisposta = new DatagramPacket(bufferRisposta, bufferRisposta.length);
                pacchettoRisposta.setAddress(InetAddress.getLocalHost());
                pacchettoRisposta.setPort(2003);
                gestore.send(pacchettoRisposta); 
            }
            


            
        }
        
        
        
    }
}