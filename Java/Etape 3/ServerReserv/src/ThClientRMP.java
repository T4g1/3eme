import Entity.Order;
import Entity.Room;
import Entity.Voyageur;
import Utils.Common;
import Utils.Request;
import Utils.Sign;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Gestion des connections de client
 */
public class ThClientRMP extends Thread {
    private final Socket sock;
    private final ServerReserv parent;
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
        String login = "";
        Request requ;
        System.out.println("Client RMP lance pour le socket " + sock.getPort());
        
        isLogged = false;
        
        while(true) {
            // Reception de la requete
            requ = Request.recv(sock);

            System.out.println(sock.getPort() + ": Commande recue: " + requ.getCommande());

            // DEMANDE DE LOGIN
            if(requ.getCommande().compareTo("LOGIN") == 0) {
                login = requ.getStringArg(0);
                byte[] password = requ.getArg(1);

                if(verifyLogin(login, password)) {
                    System.out.println(sock.getPort() + ": Authentification reussie");
                }
                else {
                    System.out.println(sock.getPort() + ": Echec de l'authentification");
                    break;
                }
            }
            // DEMANDE LA LISTE DES CHAMBRES LIBRES
            else if(requ.getCommande().compareTo("GET_AVAILABLE_ROOMS") == 0) {
                if(!isLogged) {
                    System.out.println("L'utilisateur doit s'authentifier pour utiliser cette commande ...");
                    continue;
                }
                
                System.out.println(sock.getPort() + ": Envois des chambres disponnibles ...");
                if(sendAvailableRooms()) {
                    System.out.println("Chambres disponnibles envoyees au client");
                }
                else {
                    System.out.println("Erreur: Chambres disponnibles non envoyees");
                    break;
                }
            }
            // DEMANDE LA LISTE DES RESERVATIONS PASSEES
            else if(requ.getCommande().compareTo("LROOM") == 0 && !login.equals("webservice")) {
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
            // DEMANDE LA LISTE DES UTILISATEURS
            else if(requ.getCommande().compareTo("GET_LIST_VOYAGEURS") == 0) {
                if(!isLogged) {
                    System.out.println("L'utilisateur doit s'authentifier pour utiliser cette commande ...");
                    continue;
                }
                
                System.out.println(sock.getPort() + ": Envois des voyageurs ...");
                if(sendListUsers()) {
                    System.out.println("Voyageurs envoyees au client");
                }
                else {
                    System.out.println("Erreur: Voyageurs non envoyees");
                    break;
                }
            }
            // RESERVATION D'UNE CHAMBRE
            else if(requ.getCommande().compareTo("BROOM") == 0) {                
                if(!isLogged) {
                    System.out.println("L'utilisateur doit s'authentifier pour utiliser cette commande ...");
                    continue;
                }
                
                System.out.println(sock.getPort() + ": Reservation de chambre ...");
                int roomId = Integer.parseInt(requ.getStringArg(0));
                int userId = Integer.parseInt(requ.getStringArg(1));
                int day = Integer.parseInt(requ.getStringArg(2));
                int month = Integer.parseInt(requ.getStringArg(3));
                int year = Integer.parseInt(requ.getStringArg(4));
                int duree = Integer.parseInt(requ.getStringArg(5));

                // Ajout de la reservation
                if(parent.addReservation(roomId, userId, day, month, year, duree)) {
                    System.out.println("Reservation effectuee");
                    Request.quickSend("BROOM_OK", sock);
                }
                else {
                    System.out.println("Reservation echouee");
                    Request.quickSend("BROOM_FAILED", sock);
                }
            }
            // PAYEMENT D'UNE RESERVATION
            else if(requ.getCommande().compareTo("PROOM") == 0) {
                if(!isLogged) {
                    System.out.println("L'utilisateur doit s'authentifier pour utiliser cette commande ...");
                    continue;
                }
                
                int orderId = Integer.parseInt(requ.getStringArg(0));
                String cardNumber = requ.getStringArg(1);

                System.out.println(sock.getPort() + ": Payement de la reservation " + orderId + ", card number: " + cardNumber);

                if(parent.payOrder(orderId, cardNumber)) {
                    System.out.println("Payement effectue");
                    Request.quickSend("PROOM_OK", sock);
                }
                else {
                    System.out.println("Payement echouee");
                    Request.quickSend("PROOM_FAILED", sock);
                }
            }
            // PAYEMENT D'UNE RESERVATION
            else if(requ.getCommande().compareTo("CROOM") == 0) {
                if(!isLogged) {
                    System.out.println("L'utilisateur doit s'authentifier pour utiliser cette commande ...");
                    continue;
                }
                
                int orderId = Integer.parseInt(requ.getStringArg(0));

                System.out.println(sock.getPort() + ": Annulation de la reservation " + orderId);

                if(parent.cancelOrder(orderId)) {
                    System.out.println("Annulation effectue");
                    Request.quickSend("CROOM_OK", sock);
                }
                else {
                    System.out.println("Annulation echouee");
                    Request.quickSend("CROOM_FAILED", sock);
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

        System.out.println("Client RMP termine pour le socket " + sock.getPort());
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
            Request reply = new Request("LOGIN_SUCCESS");
            reply.addArg(Sign.getEncryptedKey());
            reply.send(sock, false);
            return true;
        }
        
        new Request("LOGIN_FAILED").send(sock, false);
        return false;
    }

    /**
     * Donne la liste des chambres disponnible au client
     */
    private boolean sendAvailableRooms() {
        Request requ = new Request("AVAILABLE_ROOMS");
        
        // Création des arguments de la requete
        ArrayList<Room> l_room = parent.getAvailableRoomList();
        for(Room room: l_room) {
            requ.addArg(Common.toByte(room));
        }
        
        return requ.send(sock);
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

    /**
     * Donne la liste des utilisateurs
     */
    private boolean sendListUsers() {
        Request requ = new Request("LIST_VOYAGEURS");
        
        // Création des arguments de la requete
        ArrayList<Voyageur> l_users = parent.getUsersList();
        for(Voyageur user: l_users) {
            requ.addArg(Common.toByte(user));
        }
        
        return requ.send(sock);
    }
}
