package progettochat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class CGestoreChat {
    private List<CPacchetto> ListaPacchettiConnessione;
    private List<CPacchetto> ListaPacchettiComunicazione;
    private List<CPacchetto> ListaPacchettiFineConnessione;
    
    private InetAddress indirizzo;
    
    private boolean connessioneLibera;
    private boolean terminaTentativoConnessione;

    public CGestoreChat() {
        this.ListaPacchettiConnessione = new ArrayList<CPacchetto>();
        this.ListaPacchettiComunicazione = new ArrayList<CPacchetto>();
        this.ListaPacchettiFineConnessione = new ArrayList<CPacchetto>();
        
        this.indirizzo = null;
        
        this.connessioneLibera = true;
        this.terminaTentativoConnessione = false;
    }
    
    synchronized public void InserisciPacchetto(String datiStringa){
        CPacchetto pacchettoDaInserire = CPacchetto.fromCSV(datiStringa);
        if(pacchettoDaInserire.getOperazione().equals("c")) this.ListaPacchettiConnessione.add(pacchettoDaInserire);
        else if(pacchettoDaInserire.getOperazione().equals("m")) this.ListaPacchettiComunicazione.add(pacchettoDaInserire);
        else if(pacchettoDaInserire.getOperazione().equals("e")) this.ListaPacchettiFineConnessione.add(pacchettoDaInserire);
    }
    
    public void InstauraConnessione(String indirizzoIP) throws UnknownHostException{
        System.out.println("INIZIO CONNESSIONE con " + indirizzoIP);
        
        if(this.connessioneLibera){
            //...
            //this.indirizzo = InetAddress.getByName(indirizzoIP); DA SOSTITUIRE CON LA RIGA SEGUENTE ----------------------
            this.indirizzo = InetAddress.getLocalHost();
            
            //Cerco se la vuole instaurare anche lui, se no => richiedo la connessione
            
            while(!terminaTentativoConnessione){
                //invio pacchetto
                //aspetto pacchetto
                //invio risposta
            }
            
            this.connessioneLibera = false;
        }
    }
}