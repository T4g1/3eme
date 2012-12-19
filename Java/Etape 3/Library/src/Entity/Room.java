package Entity;

import java.io.Serializable;

/**
 * Stocke une chambre
 */
public class Room implements Serializable {
    private int id;
    private String categorie;
    private int douche;
    private int baignoire;
    private int cuvette;
    private int places;
    private int prix;
    
    //<editor-fold defaultstate="collapsed" desc="Constructeurs">
    
    /**
     * Constructeur
     */
    public Room() {
        this(0);
    }
    public Room(int id) {
        this.id = id;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Accesseurs">
    
    public int getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getDouche() {
        return douche;
    }

    public int getBaignoire() {
        return baignoire;
    }

    public int getCuvette() {
        return cuvette;
    }

    public int getPlaces() {
        return places;
    }

    public int getPrix() {
        return prix;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    public void setId(int value) {
        if(value >= 0) {
            id = value;
        }
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDouche(int douche) {
        this.douche = douche;
    }

    public void setBaignoire(int baignoire) {
        this.baignoire = baignoire;
    }

    public void setCuvette(int cuvette) {
        this.cuvette = cuvette;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    
    //</editor-fold>
}
