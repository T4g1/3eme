package Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Stocke une réservation
 */
public final class Order implements Serializable {
    private int id;
    private int titulaire;
    private int numeroChambre;
    private boolean isPaye;
    private String usernameTitulaire;
    private Date debut;
    private int duree;
    private int status;
    
    //<editor-fold defaultstate="collapsed" desc="Constructeurs">
    
    /**
     * Constructeur
     */
    public Order() {
        this(0);
    }
    public Order(int id) {
        setId(id);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Accesseurs">
    
    public int getId() {
        return id;
    }
    
    public int getTitulaire() {
        return titulaire;
    }
    
    public String getUsernameTitulaire() {
        return usernameTitulaire;
    }
    
    public int getNumeroChambre() {
        return numeroChambre;
    }
    
    public boolean isPaye() {
        return isPaye;
    }

    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public int getStatus() {
        return status;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    public void setId(int value) {
        if(value >= 0) {
            id = value;
        }
    }
    
    public void setTitulaire(int value) {
        if(value >= 0) {
            titulaire = value;
        }
    }
    
    public void setUsernameTitulaire(String value) {
        usernameTitulaire = value;
    }
    
    public void setNumeroChambre(int value) {
        if(value >= 0) {
            numeroChambre = value;
        }
    }
    
    public void setIsPaye(boolean value) {
        isPaye = value;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    //</editor-fold>

}
