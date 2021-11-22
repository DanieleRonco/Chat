package chattecnologie;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CGestioneChat {
    private String nickname;
    
    private CGestorePacchetto gestorePacchetto;
    
    private InetAddress indirizzoDestinatario;
    private String nicknameDestinatario;
    
    public CGestioneChat(String nickname, CGestorePacchetto gestorePacchetto) {
        this.nickname = nickname;
        
        this.gestorePacchetto = gestorePacchetto;
        
        this.indirizzoDestinatario = null;
        this.nicknameDestinatario = "";
    }

    public String getNickname() {
        return nickname;
    }
    public CGestorePacchetto getGestorePacchetto() {
        return gestorePacchetto;
    }
    public InetAddress getIndirizzoDestinatario() {
        return indirizzoDestinatario;
    }
    public String getNicknameDestinatario() {
        return nicknameDestinatario;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setGestorePacchetto(CGestorePacchetto gestorePacchetto) {
        this.gestorePacchetto = gestorePacchetto;
    }
    public void setIndirizzoDestinatario(InetAddress indirizzoDestinatario) {
        this.indirizzoDestinatario = indirizzoDestinatario;
    }
    public void setNicknameDestinatario(String nicknameDestinatario) {
        this.nicknameDestinatario = nicknameDestinatario;
    }
    
    public void InstauraConnessione(String indirizzo) throws UnknownHostException, IOException{
        this.indirizzoDestinatario = InetAddress.getByName("localhost");
                
        ThreadInstauraConnessione tic1 = new ThreadInstauraConnessione(this, this.gestorePacchetto);
        tic1.start();
        
        System.out.println("Digitare 'Annulla' per interrompere il tentativo di connessione");
        Scanner jin = new Scanner(System.in);
        String annulla = jin.nextLine();
        
        if(annulla.equals("Annulla")){
            tic1.interrupt();
            ChiudiConnessione();
            
            System.out.println("Tentativo di connessione anullato");
        }
    }
    
    public void ChiudiConnessione() throws IOException{
        gestorePacchetto.InvioPacchetto("e;", this.indirizzoDestinatario);
    }
}