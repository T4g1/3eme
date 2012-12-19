package supervisionpetra;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestion du PETRA a travers le réseau
 * @author T4g1
 */
public class Petra {
    public final static int CP = 0;
    public final static int C1 = 2;
    public final static int C2 = 3;
    public final static int PV = 4;
    public final static int PA = 5;
    public final static int AA = 6;
    public final static int GA = 7;
    
    private String ip;
    private int port;
    private int actuateurs;
    private Socket socket;
    private OutputStream oStream;
    
    public Petra(String ip, int port)
    {        
        actuateurs = 0;
        
        this.ip = ip;
        this.port = port;
        
        try {
            // Connection au petra
            socket = new Socket(ip, port);
            oStream = socket.getOutputStream();
        } catch (UnknownHostException ex) {
            System.out.println("Erreur d'ip");
        } catch (IOException ex) {
            System.out.println("Erreur d'accept");
        }
    }
    
    /**
     * Envois le message au PETRA
     * 
     * @param message   Message que l'on envoit
     */
    private void send(String message)
    {
        int num = Integer.parseInt(message);
        String bin = Integer.toBinaryString(num);
        
        int size = bin.length();
        for(int i=0; i<8-size; i++) {
            bin = "0" + bin;
        }
        System.out.println("Valeur envoyée: " + bin + ", en décimal: " + message);
        
        try {
            oStream.write(message.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Petra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Allume ou eteint le PETRA
     */
    public void switchState()
    {
        send("SWITCH_STATE");
    }
    
    /**
     * Modifie la valeur du bit donné
     * 
     * @param bit       Actuateur ciblé
     * @param value     Nouvelle valeur
     */
    public void setActuateur(int bit, boolean value)
    {
        if(value) { // Mise a 1
            actuateurs |= (1 << bit);
        }
        else {      // Mise a 0
            actuateurs &= ~(1 << bit);
        }
        
        // Seul l'envoi de CP ne change pas les actuateurs
        // l'envoi de CP + 1 doit etre fait pour changer la valeur
        if(bit != CP)
        {
            System.out.println("Activateur changé: " + CP + " Envoi de " + bit);
            send(String.valueOf(actuateurs));
        }
    }
}
