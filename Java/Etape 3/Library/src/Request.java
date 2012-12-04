import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Requêtes transitant sur le réseau
 */
public class Request implements Serializable {
    private byte[] signature;
    private byte[] commande;
    private ArrayList<byte[]> args;
    private boolean isEncrypted;
    
    /**
     * Constructeur de la requête
     */
    public Request(String commande) {
        signature = null;
        
        setCommande(commande);
        
        args = new ArrayList<>();
        
        isEncrypted = false;
    }

    private Request() {
        this("NO_COMMAND");
    }
    
    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    /**
     * Indique la commande voulue
     * @param commande  Commande voulue
     * @return          false si le message doit d'abord être décrypté
     */
    public final boolean setCommande(String commande) {
        if(isEncrypted) {
            return false;
        }
        
        this.commande = commande.getBytes();
        return true;
    }
    
    /**
     * Ajoute un argument à la requete
     * @param arg   Argument que l'on veut ajouter
     * @return      false si le message doit d'abord être décrypté
     */
    public boolean addArg(String arg) {
        return addArg(arg.getBytes());
    }
    
    public boolean addArg(byte[] arg) {
        if(isEncrypted) {
            return false;
        }
        
        args.add(arg);
        return true;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Cryptage">
    
    /**
     * Encrypte la requete
     * @return          false si la requête est déjà encryptée
     */
    public boolean encrypt() {
        if(isEncrypted) {
            return false;
        }
        
        isEncrypted = true;
        return true;
    }
    
    /**
     * Decrypte la requete
     * @return          false si la requête est déjà decryptée
     */
    public boolean decrypt() {
        if(!isEncrypted) {
            return false;
        }
        
        isEncrypted = false;
        return true;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Accesseurs">

    /**
     * Donne la commande
     */
    public String getCommande() {
        // Decryptage
        if(isEncrypted) {
            decrypt();
        }
        
        return Common.byteToString(commande);
    }

    /**
     * Donne l'argument a l'index donné
     * @param i     Index   
     * @return      Argument voulu
     */
    public byte[] getArg(int i) {
        // Decryptage
        if(isEncrypted) {
            decrypt();
        }
        
        // Argument inexistant
        if(args.size() <= i) {
            return null;
        }
        
        return args.get(i);
    }
    
    public String getStringArg(int i) {
        return Common.byteToString(getArg(i));
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Utils">
    /**
     * Donne le digest du string donné
     * @param password
     * @return      Tableau de byte
     */
    public static byte[] getDigest(String password) {
        // Brouille le mot de passe
        password += "CowLAN";
        
        // Construit le digest du mot de passe
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(password.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Algo SHA1 inexistant, mot de passe non transmis");
        }
        
        return null;
    }
    
    /**
     * Envois l'objet sur le flux donné
     * @param out
     * @throws IOException  Erreur d'ecriture
     */
    public void send(ObjectOutputStream out) throws IOException {
        encrypt();
        out.writeObject(this);
        out.flush();
    }
    
    /**
     * Recois des données sur le flux donné
     * @param in
     * @return      Donnée lues
     * @throws IOException  Erreur de lecture
     */
    public static Request recv(ObjectInputStream in) throws IOException {
        Request requ;
        
        try {
            requ = (Request)in.readObject();
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe Request introuvable");
            requ = new Request();
        }
        
        requ.decrypt();
        
        return requ;
    }
    
    //</editor-fold>
}
