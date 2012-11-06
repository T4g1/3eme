package supervisionpetra;

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
    
    public Petra(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
        
        actuateurs = 0;
    }
    
    /**
     * Envois le message au PETRA
     * 
     * @param message   Message que l'on envoit
     */
    private void send(String message)
    {
        System.out.println(message);
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
            send(String.valueOf(actuateurs));
        }
    }
}
