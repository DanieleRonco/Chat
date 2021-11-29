package progettochat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class CGestoreChat {
    private CUDP gestoreUDP;
    
    public FrameGrafica frameGrafica;
    
    private List<CPacchetto> ListaPacchettiConnessione;
    private List<CPacchetto> ListaPacchettiComunicazione;
    private List<CPacchetto> ListaPacchettiFineConnessione;
    
    private InetAddress IndirizzoDestinatario;
    
    private boolean connessioneLibera;
    private boolean terminaTentativoConnessione;
    
    private String mioNome;
    private String nomeDestinatario;

    public CGestoreChat() throws SocketException {
        this.gestoreUDP = new CUDP();
        
        this.frameGrafica = null;
        
        this.ListaPacchettiConnessione = new ArrayList<CPacchetto>();
        this.ListaPacchettiComunicazione = new ArrayList<CPacchetto>();
        this.ListaPacchettiFineConnessione = new ArrayList<CPacchetto>();
        
        this.IndirizzoDestinatario = null;
        
        this.connessioneLibera = true;
        this.terminaTentativoConnessione = false;
        
        this.mioNome = "";
        this.nomeDestinatario = "";
    }
    
    public CUDP getGestoreUDP(){
        return this.gestoreUDP;
    }
    
    public InetAddress getIndirizzoDestinatario(){
        return this.IndirizzoDestinatario;
    }
    
    public String getIndirizzoStringa(){
        String IndirizzoDestinatarioStringa = this.IndirizzoDestinatario.toString();
        int indice = IndirizzoDestinatarioStringa.indexOf("/");
        return IndirizzoDestinatarioStringa.substring(indice + 1, IndirizzoDestinatarioStringa.length());
    }

    public boolean isTerminaTentativoConnessione() {
        return terminaTentativoConnessione;
    }

    public boolean isConnessioneLibera() {
        return connessioneLibera;
    }

    public String getMioNome() {
        return mioNome;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }
    
    public void setFrameGrafica(FrameGrafica frameGrafica){
        this.frameGrafica = frameGrafica;
    }

    public void setTerminaTentativoConnessione(boolean terminaTentativoConnessione) {
        this.terminaTentativoConnessione = terminaTentativoConnessione;
    }

    public void setConnessioneLibera(boolean connessioneLibera) {
        this.connessioneLibera = connessioneLibera;
    }

    public void setMioNome(String mioNome) {
        this.mioNome = mioNome;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }
    
    synchronized public void InserisciPacchetto(String datiStringa) throws UnknownHostException{
        CPacchetto pacchettoDaInserire = CPacchetto.fromCSV(datiStringa);
        
        String operazione = pacchettoDaInserire.getOperazione();
        
        if(operazione.equals("c") || operazione.equals("y") || operazione.equals("n")) this.ListaPacchettiConnessione.add(pacchettoDaInserire);
        else if(operazione.equals("m")){
            this.ListaPacchettiComunicazione.add(pacchettoDaInserire);
        }
        else if(operazione.equals("e")) this.ListaPacchettiFineConnessione.add(pacchettoDaInserire);
        
        if(operazione.equals("c")) this.ChiediConnessione(pacchettoDaInserire.getIndirizzo());
    }
    
    synchronized public CPacchetto IsPresenteInListaPacchettiConnessione(String indirizzo){
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
        if(this.connessioneLibera){
            this.IndirizzoDestinatario = InetAddress.getByName(indirizzoIP);
            //this.IndirizzoDestinatario = InetAddress.getLocalHost();
            
            ThreadInstauraConnessione tic1 = new ThreadInstauraConnessione(this);
            tic1.start();
        }
    }
    
    synchronized public void ChiudiConnessione() throws IOException{
        this.connessioneLibera = true;
        this.gestoreUDP.InvioPacchetto("e;", IndirizzoDestinatario);
    }
    
    synchronized public String CercaPacchettiComunicazione(){
        CPacchetto pacchettoTemp = null;
        for(int i = 0; i < this.ListaPacchettiComunicazione.size(); i++){
           pacchettoTemp = this.ListaPacchettiComunicazione.get(i);
           this.ListaPacchettiComunicazione.remove(i);
        }
        if(pacchettoTemp != null) return pacchettoTemp.getMessaggio();
        else return "";
    }
    
    synchronized public boolean CercaPacchettiChiusura(){
        for(int i = 0; i < this.ListaPacchettiFineConnessione.size(); i++){
            if(this.ListaPacchettiFineConnessione.get(i).getIndirizzo().equals(this.getIndirizzoStringa())){ //QUI
                this.ListaPacchettiFineConnessione.remove(i);
                return true;
            }
        }
        return false;
    }
    
    synchronized public void EliminaPacchetti() throws UnknownHostException, IOException{
        byte[] mioIndirizzo = this.getIndirizzoStringa().getBytes();
        for(int i = 0; i < this.ListaPacchettiComunicazione.size(); i++){
            if(!this.ListaPacchettiComunicazione.get(i).equals(mioIndirizzo)){
                gestoreUDP.InvioPacchetto("e;", InetAddress.getByAddress(mioIndirizzo));
            }
        }
        for(int i = 0; i < this.ListaPacchettiConnessione.size(); i++){
            if(!this.ListaPacchettiConnessione.get(i).equals(mioIndirizzo)){
                gestoreUDP.InvioPacchetto("e;", InetAddress.getByAddress(mioIndirizzo));
            }
        }
        for(int i = 0; i < this.ListaPacchettiFineConnessione.size(); i++){
            if(!this.ListaPacchettiFineConnessione.get(i).equals(mioIndirizzo)){
                gestoreUDP.InvioPacchetto("e;", InetAddress.getByAddress(mioIndirizzo));
            }
        }
    }
    
    synchronized public void InviaMessaggio(String messaggio) throws IOException{
        if(!this.connessioneLibera) gestoreUDP.InvioPacchetto(messaggio, IndirizzoDestinatario);
    }
    
    synchronized public void ChiediConnessione(String indirizzo) throws UnknownHostException{
        if(this.frameGrafica.chiedi()) this.InstauraConnessione(indirizzo);
        
    }
}