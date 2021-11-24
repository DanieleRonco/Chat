package progettochat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class CGestoreChat {
    private CUDP gestoreUDP;
    
    public FrameGrafica frame;
    
    private List<CPacchetto> ListaPacchettiConnessione;
    private List<CPacchetto> ListaPacchettiComunicazione;
    private List<CPacchetto> ListaPacchettiFineConnessione;
    
    private InetAddress indirizzo;
    
    private boolean connessioneLibera;
    private boolean terminaTentativoConnessione;

    public CGestoreChat() throws SocketException {
        this.gestoreUDP = new CUDP();
        
        this.frame = null;
        
        this.ListaPacchettiConnessione = new ArrayList<CPacchetto>();
        this.ListaPacchettiComunicazione = new ArrayList<CPacchetto>();
        this.ListaPacchettiFineConnessione = new ArrayList<CPacchetto>();
        
        this.indirizzo = null;
        
        this.connessioneLibera = true;
        this.terminaTentativoConnessione = false;
    }
    
    public CUDP getGestoreUDP(){
        return this.gestoreUDP;
    }
    
    public InetAddress getIndirizzo(){
        return this.indirizzo;
    }
    
    public String getIndirizzoStringa(){
        return this.indirizzo.toString();
    }

    public boolean isTerminaTentativoConnessione() {
        return terminaTentativoConnessione;
    }

    public boolean isConnessioneLibera() {
        return connessioneLibera;
    }
    
    public void setFrame(FrameGrafica frame){
        this.frame = frame;
    }

    public void setTerminaTentativoConnessione(boolean terminaTentativoConnessione) {
        this.terminaTentativoConnessione = terminaTentativoConnessione;
    }

    public void setConnessioneLibera(boolean connessioneLibera) {
        this.connessioneLibera = connessioneLibera;
    }
    
    synchronized public void InserisciPacchetto(String datiStringa){
        CPacchetto pacchettoDaInserire = CPacchetto.fromCSV(datiStringa);
        String operazione = pacchettoDaInserire.getOperazione();
        if(operazione.equals("c") || operazione.equals("y") || operazione.equals("n")) this.ListaPacchettiConnessione.add(pacchettoDaInserire);
        else if(operazione.equals("m")){
            this.ListaPacchettiComunicazione.add(pacchettoDaInserire);
            System.out.println("PACCHETTO MESSAGGIO ARRIVATO E INSERITO");
        }
        else if(operazione.equals("e")) this.ListaPacchettiFineConnessione.add(pacchettoDaInserire);
    }
    
    synchronized public void EliminaTentativiConnessioni(){
        if(!connessioneLibera){
            String indirizzo = getIndirizzoStringa();
            for(int i = 0; i < this.ListaPacchettiConnessione.size(); i++){
                if(!this.ListaPacchettiConnessione.get(i).getIndirizzo().equals(indirizzo)){
                    this.ListaPacchettiConnessione.remove(i);
                }
            }
        }
        
    }
    
    synchronized public CPacchetto IsPresenteInListaPacchettiConnessione(String indirizzo){
        int indice = indirizzo.indexOf("/");
        indirizzo = indirizzo.substring(indice + 1, indirizzo.length());
        for(int i = 0; i < this.ListaPacchettiConnessione.size(); i++){
            if(this.ListaPacchettiConnessione.get(i).getIndirizzo().equals(indirizzo)) {
                CPacchetto pacchettoTemp = this.ListaPacchettiConnessione.get(i);
                this.ListaPacchettiConnessione.remove(i);
                return pacchettoTemp;
            }
        }
        return null;
    }
    
    public void InstauraConnessione(String indirizzoIP) throws UnknownHostException{
        
        
        //Se la connessione è libera = non occupata, allora provo a connettermi
        if(this.connessioneLibera){
            System.out.println("INIZIO CONNESSIONE CON " + indirizzoIP);
            //this.indirizzo = InetAddress.getByName(indirizzoIP); DA SOSTITUIRE CON LA RIGA SEGUENTE ----------------------
            this.indirizzo = InetAddress.getLocalHost();
            
            ThreadInstauraConnessione tic1 = new ThreadInstauraConnessione(this);
            tic1.start();
        }
    }
    
    public void ChiudiConnessione() throws IOException{
        
        System.out.println("Chiudo la connessione");
        this.connessioneLibera = true;
        this.gestoreUDP.InvioPacchetto("e;", indirizzo);
    }
    
    public String CercaPacchettiComunicazione(){
        CPacchetto pacchettoTemp = null;
        for(int i = 0; i < this.ListaPacchettiComunicazione.size(); i++){
           pacchettoTemp = this.ListaPacchettiComunicazione.get(i);
           this.ListaPacchettiComunicazione.remove(i);
        }
        if(pacchettoTemp != null) return pacchettoTemp.getMessaggio();
        else return "";
    }
    
    public void InviaMessaggio(String messaggio) throws IOException{
        if(!this.connessioneLibera) gestoreUDP.InvioPacchetto(messaggio, indirizzo);
    }
}