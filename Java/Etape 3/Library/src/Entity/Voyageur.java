package Entity;

import java.io.Serializable;

/**
 * Stocke un voyageur
 */
public class Voyageur implements Serializable {
    private int id;
    private String username;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    
    //<editor-fold defaultstate="collapsed" desc="Constructeurs">
    
    /**
     * Constructeur
     */
    public Voyageur() {
        this(0);
    }
    public Voyageur(int id) {
        this.id = id;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Accesseurs">

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mutateurs">

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    //</editor-fold>
}
