import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * Gestion des connections de client
 */
public class ThClientRMP extends Thread {
    private final Socket sock;
    private final ServerReserv parent;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean isLogged;
    
    /**
     * Initialisation du thread
     */
    public ThClientRMP(ServerReserv parent, Socket sock) {
        this.parent = parent;
        this.sock = sock;
    }
    
    /**
     * Traitement du client
     */
    @Override
    public void run() {
        Request requ;
        System.out.println("Client RMP lance pour le socket " + sock.getPort());
        
        try {
            // Récupére les stream
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
        } catch (IOException ex) {
            System.out.println("Impossible d'obtenir les flux de donnees ...");
            return;
        }
        
        isLogged = false;
        
        try {
            while(true) {
                // Reception de la requete
                requ = Request.recv(in);

                System.out.println(sock.getPort() + ": Commande recue: " + requ.getCommande());

                // DEMANDE DE LOGIN
                if(requ.getCommande().compareTo("LOGIN") == 0) {
                    String login = requ.getStringArg(0);
                    byte[] password = requ.getArg(1);

                    if(verifyLogin(login, password)) {
                        System.out.println(sock.getPort() + ": Authentification reussie");
                    }
                    else {
                        System.out.println(sock.getPort() + ": Echec de l'authentification");
                    }
                }
                // DEMANDE NON RECONNUE
                else {
                    new Request("UNKNOWN_COMMAND").send(out);
                }
            }
        } catch (IOException ex) {
            System.out.println("Impossible de joindre le client");
        }
        
        System.out.println("Client RMP termine pour le socket " + sock.getPort());
    }

    /**
     * Vérifie l'authentification de l'utilisateur
     * @param login
     * @param digest        Digest du password
     */
    private boolean verifyLogin(String login, byte[] digest) throws IOException {
        System.out.println(
                "Tentative d'authentification avec le login " + login +
                ", password: " + Common.byteToString(digest)
        );
        
        // Vérifie le password
        byte[] realDigest;
        realDigest = parent.getUserDigest(login);
        
        // Digest identique
        if(Arrays.equals(realDigest, digest)) {
            isLogged = true;
            new Request("LOGIN_SUCCESS").send(out);
            return true;
        }
        
        new Request("LOGIN_FAILED").send(out);
        return false;
    }
}
