/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettochat;

/**
 *
 * @author PC-Fisso
 */
public class ThreadRifiutaConnessione extends Thread{
    private CGestoreChat gestore;
    private CUDP gestoreUDP;
    
    public ThreadRifiutaConnessione(CGestoreChat gestore){
        this.gestore = gestore;
        this.gestoreUDP = gestoreUDP;
    }

    @Override
    public void run() {
        while(true){
            gestore.InserisciPacchetto("/192.168.12.22;c;nicknamee;");
            gestore.EliminaTentativiConnessioni();
        }
    }
    
    
}
