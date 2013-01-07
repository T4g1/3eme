package Session;

import Bean.BeanDBAccessMySQL;
import Utils.Request;
import Utils.Sign;
import Vues.Vues;
import java.beans.Beans;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Informations de l'utilisateur
 * 
 * @author T4g1
 */
public class UserInfo {
    public static final String USER_INFO_KEY = "USER_INFO_KEY";
    private boolean logged;
    private int id;
    private String page;
    private Socket sock;
    
    /**
     * Initialisation du bean
     */
    @PostConstruct
    private void initialize() {
        logged = false;
        page = "";
        id = 0;
    }
    
    /**
     * Récupére les informations utilisateur
     * 
     * @param request       Requete recue par le servlet
     * 
     * @return              Les informations utilisateur
     */
    public static UserInfo getUserInfo(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(true);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute(UserInfo.USER_INFO_KEY);
        if (userInfo == null) {
            userInfo = new UserInfo();
            
            httpSession.setAttribute(UserInfo.USER_INFO_KEY, userInfo);
        }
        
        return userInfo;
    }
    
    public int connect() {
        // Deconnection
        if(sock != null) {
            try {
                sock.close();
                sock = null;
                System.out.println("Deconnecte du serveur");
            } catch (IOException ex) {
                System.out.println("Impossible de se deconnecter");
            }
        }
        
        String ip = "127.0.0.1";
        int port = 40000;
        
        System.out.println("Tentative de connection au serveur " + ip + ":" + port);
        
        try {
            sock = new Socket(ip, port);
        } catch (UnknownHostException ex) {
            System.out.println("L'ip " + ip + " n'est pas valide");
            return -1;
        } catch (IOException ex) {
            System.out.println("Impossible de joindre le serveur a l'adresse " + ip + ":" + port);
            return -2;
        }
        
        System.out.println("Connection reussie");
        
        String login = "webservice";
        String password = "webservice";
        if(doLogin(login, password)) {
            System.out.println("Authentification reussie");
        }
        else {
            System.out.println("Authentification impossible, abandon de la connexion");
            return 0;
        }
        
        return 1;
    }
    
    /**
     * Tentative d'authentification de l'utilisateur
     * @param login     Login de l'utilisateur
     * @param password  Password de l'utilisateur
     */
    private boolean doLogin(String login, String password) {
        byte[] digest = Request.getDigest(password);
        
        // Construit la requete de LOGIN
        Request reply, requ = new Request("LOGIN");
        requ.addArg(login);
        requ.addArg(digest);
        
        reply = requ.sendAndRecv(sock, false);
        Sign.setKey(Sign.decryptKey(reply.getArg(0)));  // Clé symétrique recue
        
        // Vérifie la réussite de la commande
        String result = reply.getCommande();
        return result.compareTo("LOGIN_SUCCESS") == 0;
    }
    
    /**
     * Ajoute la chambre dont le numéro est donné au caddie
     * 
     * @param numero        Numero de la chambre voulue
     * 
     * @return              true si chambre commandée, false sinon
     */
    public int commander(int numero) {
        int retour = connect();
        if(retour != 1) {
            return retour - 1;
        }
        
        // Prépare la requête
        Request reply, requ = new Request("BROOM");
        requ.addArg(Integer.toString(numero));  // Chambre
        requ.addArg(Integer.toString(1));
        requ.addArg(Integer.toString(1));       // Date J
        requ.addArg(Integer.toString(1));       // Date M
        requ.addArg(Integer.toString(2014));    // Date A
        requ.addArg(Integer.toString(2));       // Nb jours
        
        reply = requ.sendAndRecv(sock);
        
        // Résultat
        if(reply.getCommande().compareTo("BROOM_OK") == 0) {
            System.out.println("Reservation enregistree");
            return 1;
        }
        else {
            System.out.println("Reservation echouee");
        }
       
        return 0;
    }
    
    /**
     * Annule le caddie actuel du client
     */
    public boolean cancelCaddie() {
        // Création de la requête
        Request reply, requ = new Request("CROOM");
        requ.addArg(Integer.toString(1));
        
        reply = requ.sendAndRecv(sock);
        
        // Résultat
        if(reply.getCommande().compareTo("CROOM_OK") == 0) {
            System.out.println("Reservation annulee");
        }
        else {
            System.out.println("Impossible d'annuler la reservation");
            return false;
        }
        
        return true;
    }
    
    /**
     * Paye le caddie actuel du client
     */
    public boolean payerCaddie() {
        // Création de la requête
        Request reply, requ = new Request("PROOM");
        requ.addArg(Integer.toString(1));
        requ.addArg("AAAA-BBBB-CCCC-DDDD");
        
        reply = requ.sendAndRecv(sock);
        
        // Résultat
        if(reply.getCommande().compareTo("PROOM_OK") == 0) {
            System.out.println("Payement valide");
        }
        else {
            System.out.println("Payement echouee");
            return false;
        }
        
        return true;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Accesseurs">
    
    /**
     * Indique si l'utilisateur est loggué
     * 
     * @return      true s'il est loggué, false sinon
     */
    public boolean isLogged() {
        return logged;
    }
    
    /**
     * Donne la page actuelle
     * 
     * @return      Page actuelle
     */
    public String getPage() {
        return page;
    }
    
    /**
     * Donne le titre de la page actuelle
     * 
     * @return      Titre de la page
     */
    public String getPageTitle() {
        return Vues.getPageTitle(page);
    }
    
    /**
     * Donne le caddie du joueur
     * 
     * @return      Caddie du joueur
     */
    public List<Integer> getCaddie() {
        List<Integer> caddie = new ArrayList<Integer>();
        
        // Ajoute les réservation non payées au caddie
        try {
            BeanDBAccessMySQL dba = (BeanDBAccessMySQL)Beans.instantiate(
                    null, "Bean.BeanDBAccessMySQL");
            
            if(dba.init()) {
                ResultSet result = dba.selectAll(
                        "reservations", "paye=0 AND titulaire=" + id);
                
                while(result.next())
                {
                    int chambre = result.getInt("chambre");
                    caddie.add(result.getInt("chambre"));
                }
            }
        }
        catch (SQLException ex) { }
        catch (IOException ex) { }
        catch (ClassNotFoundException ex) { }
        
        return caddie;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    /**
     * Modifie la valeur de logged
     * 
     * @param value         Nouvelle valeur
     */
    public void setLogged(boolean value) {
        logged = value;
    }
    
    /**
     * Modifie la page actuelle
     * 
     * @param value         Nouvelle page
     */
    public void setPage(String value) {
        page = value;
    }
    
    /**
     * Modifie l'id du client
     * 
     * @param value         Nouvel id
     */
    public void setId(int value) {
        id = value;
    }
    
    //</editor-fold>
}
