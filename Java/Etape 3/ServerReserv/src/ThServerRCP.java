
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread pour les consultations depuis un village
 */
public class ThServerRCP extends Thread {
    ExecutorService pool = Executors.newFixedThreadPool(7);
    private int PORT_VILLAGE;
    private boolean stopRequested;
    private ServerSocket sock;
    private final ServerReserv parent;
    
    public ThServerRCP(ServerReserv parent, int port) {
        // Vérifie le port
        if(port < 40000) {
            port = 40000;
        }
        
        PORT_VILLAGE = port;
        stopRequested = false;
        
        this.parent = parent;
    }
    
    /**
     * Boucle principale
     */
    @Override
    public void run() {
        System.out.println("Demarrage du thread RCP");
        
        try {
            sock = new ServerSocket(PORT_VILLAGE);
        } catch (IOException ex) {
            System.out.println("Impossible de creer le socket RCP");
        }    
        
        // Boucle principale
        while(!stopRequested) {
            try {
                Socket s = sock.accept();
                System.out.println("Nouvelle connection RCP");

                pool.execute(new ThClientRCP(parent, s));
            } catch (IOException ex) {
                System.out.println("Interruption lors de l'accept RCP");
            }
        }
    }
    
    /**
     * Termine le serveur
     */
    public synchronized void requestStop() throws IOException {
        stopRequested = true;
        sock.close();
    }
}
