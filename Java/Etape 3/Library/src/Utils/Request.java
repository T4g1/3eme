package Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

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
    public boolean encrypt(SecretKey secretKey) {
        if(isEncrypted) {
            return false;
        }
        
        try {
            // Initialise le chiffrement
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            // Chiffre les arguments
            for(int i=0; i<args.size(); i++) {
                try {
                    args.set(
                            i, cipher.doFinal(args.get(i))
                    );
                } catch (IllegalBlockSizeException | BadPaddingException ex) {
                    System.out.println("Impossible de chiffrer l'argument: " + args.get(i));
                }
            }
        } catch (InvalidKeyException ex) {
            System.out.println("Requ cryptKey: Cle invalide");
        } catch (NoSuchProviderException ex) {
            System.out.println("Provider non disponnible");
        } catch (NoSuchPaddingException ex) {
            System.out.println("Padding non disponnible");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Impossible de generer les cle de cryptographier");
        }
        
        isEncrypted = true;
        return true;
    }
    
    /**
     * Decrypte la requete
     * @return          false si la requête est déjà decryptée
     */
    public boolean decrypt(SecretKey secretKey) {
        if(!isEncrypted) {
            return false;
        }
        
        try {
            // Initialise le chiffrement
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            // Chiffre les arguments
            for(int i=0; i<args.size(); i++) {
                try {
                    args.set(
                            i, cipher.doFinal(args.get(i))
                    );
                } catch (IllegalBlockSizeException | BadPaddingException ex) {
                    System.out.println("Impossible de dechiffrer l'argument: " + args.get(i));
                }
            }
        } catch (InvalidKeyException ex) {
            System.out.println("Requ decryptKey: Cle invalide");
        } catch (NoSuchProviderException ex) {
            System.out.println("Provider non disponnible");
        } catch (NoSuchPaddingException ex) {
            System.out.println("Padding non disponnible");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Impossible de generer les cle de cryptographier");
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
        return Common.byteToString(commande);
    }
    
    /**
     * Donne la signature du message
     * @return  Signature
     */
    private byte[] getSignature() {
        return signature;
    }

    /**
     * Donne l'argument a l'index donné
     * @param i     Index   
     * @return      Argument voulu
     */
    public byte[] getArg(int i) {
        // Argument inexistant
        if(args.size() <= i) {
            return null;
        }
        
        return args.get(i);
    }
    
    public String getStringArg(int i) {
        return Common.byteToString(getArg(i));
    }

    /**
     * Donne la liste des arguments
     * @return 
     */
    public ArrayList<byte[]> getArgs() {
        return args;
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
            System.out.println("Algo SHA1 inexistant, digest non transmis");
        }
        
        return null;
    }
    
    /**
     * Envois l'objet sur le flux donné
     * @param sock
     * @throws IOException  Erreur d'ecriture
     */
    public boolean send(Socket sock) {
        return send(sock, true);
    }
    public boolean send(Socket sock, boolean crypt) {
        // Verifie l'argument
        if(sock == null) {
            return false;
        }
        
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(sock.getOutputStream());
        
            if(crypt) {
                encrypt(Sign.getKey());
            }
            
            // Signe le message
            signature = Sign.sign(this.toString().getBytes());
        
            out.writeObject(this);
            out.flush();
            
            return true;
        } catch (IOException ex) {
            System.out.println(this.getCommande() + " non envoye, erreur lors du send");
            return false;
        }
    }
    
    /**
     * Recois des données sur le flux donné
     * @param sock
     * @return      Donnée lues
     * @throws IOException  Erreur de lecture
     */
    public static Request recv(Socket sock) {
        return recv(sock, true);
    }
    public static Request recv(Socket sock, boolean decrypt) {
        // Verifie l'argument
        if(sock == null) {
            return new Request("SOCK_NULL");
        }
        
        try {
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
            Request requ = (Request)in.readObject();
            
            // Vérifie la signature
            if(!Sign.verify(requ.toString().getBytes(), requ.getSignature())) {
                System.out.println("Message signe: PAS OK");
                return new Request();
            }
        
            if(decrypt) {
                requ.decrypt(Sign.getKey());
            }

            return requ;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Erreur lors du recv");
            return new Request();
        }
    }
    
    /**
     * Combine le send et le recv
     * @param sock
     * @return 
     */
    public Request sendAndRecv(Socket sock) {
        return sendAndRecv(sock, true);
    }
    public Request sendAndRecv(Socket sock, boolean encrypted) {
        if(send(sock, encrypted)) {
            return recv(sock, encrypted);
        } else {
            return new Request();
        }
    }
    
    /**
     * Demande au serveur sans paramétre
     * @param commande
     * @param sock 
     */
    public static void quickSend(String commande, Socket sock) {
        Request request = new Request(commande);
        request.send(sock);
    }
    
    /**
     * Retourne une chaine de caractére représentant l'objet
     * @return      Objet String représentant l'objet
     */
    @Override
    public String toString () {
        // Ajoute la commande dans le string
        String string = new String(commande);
        
        // Ajoute les arguments
        for(byte[] arg: args) {
            string += new String(arg);
        }
        
        return string;
    }
    
    //</editor-fold>
}
