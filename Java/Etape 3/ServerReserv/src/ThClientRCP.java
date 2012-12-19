import Entity.Order;
import Entity.Room;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Gestion des connections de client
 */
public class ThClientRCP extends Thread {
    private final Socket sock;
    private final ServerReserv parent;
    private boolean isLogged;
    
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
        Request requ;
        System.out.println("Client RCP lance pour le socket " + sock.getLocalPort());
        
        isLogged = false;
        
        while(true) {
            // Reception de la requete
            requ = Request.recv(sock);

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
                    break;
                }
            }
            // DEMANDE LA LISTE DES RESERVATIONS PASSEES
            else if(requ.getCommande().compareTo("BDROOM") == 0) {
                if(!isLogged) {
                    System.out.println("L'utilisateur doit s'authentifier pour utiliser cette commande ...");
                    continue;
                }
                
                System.out.println(sock.getPort() + ": Envois des reservations ...");
                if(sendListOrder()) {
                    System.out.println("Reservations envoyees au client");
                }
                else {
                    System.out.println("Erreur: Reservations non envoyees");
                    break;
                }
            }
            // ARRIVEE DU CLIENT
            else if(requ.getCommande().compareTo("ARRROOM") == 0) {                
                if(!isLogged) {
                    System.out.println("L'utilisateur doit s'authentifier pour utiliser cette commande ...");
                    continue;
                }
                
                System.out.println(sock.getPort() + ": Client arrive ...");
                int orderId = Integer.parseInt(requ.getStringArg(0));

                // Ajout de la reservation
                if(parent.setOrderStatus(orderId, true)) {
                    System.out.println("Mise a present de la reservation effectuee");
                    Request.quickSend("OK", sock);
                }
                else {
                    System.out.println("Mise a present de la reservation echouee");
                    Request.quickSend("FAILED", sock);
                }
            }
            // CLIENT ABSENT
            else if(requ.getCommande().compareTo("MISROOM") == 0) {
                if(!isLogged) {
                    System.out.println("L'utilisateur doit s'authentifier pour utiliser cette commande ...");
                    continue;
                }
                
                System.out.println(sock.getPort() + ": Client absent ...");
                int orderId = Integer.parseInt(requ.getStringArg(0));

                // Ajout de la reservation
                if(parent.setOrderStatus(orderId, false)) {
                    System.out.println("Mise a absent de la reservation effectuee");
                    Request.quickSend("OK", sock);
                }
                else {
                    System.out.println("Mise a absent de la reservation echouee");
                    Request.quickSend("FAILED", sock);
                }
            }
            // DEMANDE NON RECONNUE
            else {
                if(!new Request("UNKNOWN_COMMAND").send(sock)) {
                    System.out.println(sock.getPort() + ": Commande inconnue recue");
                    break;
                }
            }
        }
        
        System.out.println("Client RCP termine pour le socket " + sock.getLocalPort());
    }

    /**
     * Vérifie l'authentification de l'utilisateur
     * @param login
     * @param digest        Digest du password
     */
    private boolean verifyLogin(String login, byte[] digest) {
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
            new Request("LOGIN_SUCCESS").send(sock);
            return true;
        }
        
        new Request("LOGIN_FAILED").send(sock);
        return false;
    }

    /**
     * Donne la liste des réservations
     */
    private boolean sendListOrder() {
        Request requ = new Request("LROOM");
        
        // Création des arguments de la requete
        ArrayList<Order> l_order = parent.getOrderList();
        for(Order order: l_order) {
            requ.addArg(Common.toByte(order));
        }
        
        return requ.send(sock);
    }
}
