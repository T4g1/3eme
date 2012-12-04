import java.net.Socket;

/**
 * Gestion des connections de client
 */
public class ThClientRCP extends Thread {
    private final Socket sock;
    private final ServerReserv parent;
    
    /**
     * Initialisation du thread
     */
    public ThClientRCP(ServerReserv parent, Socket sock) {
        this.parent = parent;
        this.sock = sock;
    }
    
    /**
     * Traitement du client
     */
    @Override
    public void run() {
        System.out.println("Client RCP lance pour le socket " + sock.getLocalPort());
        
        System.out.println("Client RCP termine pour le socket " + sock.getLocalPort());
    }
}
