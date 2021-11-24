package progettochat;

public class CPacchetto {
    private String indirizzo;
    private String operazione;
    private String messaggio;
    
    public CPacchetto(){
        this.indirizzo = "";
        this.operazione = "";
        this.messaggio = "";
    }

    public CPacchetto(String indirizzo, String operazione, String messaggio) {
        this.indirizzo = indirizzo;
        this.operazione = operazione;
        this.messaggio = messaggio;
    }

    public String getIndirizzo(){
        return indirizzo;
    }
    public String getOperazione() {
        return operazione;
    }
    public String getMessaggio() {
        return messaggio;
    }

    public void setIndirizzo(String indirizzo){
        this.indirizzo = indirizzo;
    }
    public void setOperazione(String operazione) {
        this.operazione = operazione;
    }
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
    
    public static CPacchetto fromCSV(String csv){
        int indice = csv.indexOf(";");
        String indirizzoTemp = csv.substring(1, indice);
        int indice2 = csv.indexOf(";", indice + 1);
        String operazioneTemp = csv.substring(indice + 1, indice2);
        
        String messaggioTemp = "";
        if(operazioneTemp.equals("c") || operazioneTemp.equals("m")) messaggioTemp = csv.substring(indice2 + 1, csv.length() - 1);
        
        return new CPacchetto(indirizzoTemp, operazioneTemp, messaggioTemp);
    }
}