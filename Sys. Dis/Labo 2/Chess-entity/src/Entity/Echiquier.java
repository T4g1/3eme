package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Gére un echiquier, représente une partie
 * @author T4g1
 */
@Entity
public class Echiquier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Piece[] l_piece = new Piece[16 * 16];
    private Joueur[] l_joueur = new Joueur[2];

    //<editor-fold defaultstate="collapsed" desc="Accesseurs">
    
    public Long getId() {
        return id;
    }
    
    /**
     * Donne la piéce à la position donnée
     * @param x     Absice de la piéce
     * @param y     Ordonnée de la piéce
     * @return      Piéce demandée ou null si erreur
     */
    public Piece getPiece(int x, int y) {
        for(Piece piece: l_piece) {
            if(piece.isAt(x, y)) {
                return piece;
            }
        }
        
        return null;
    }
    
    public String getNom() {
        return nom;
    }
    
    public int getPlayerCount() {
        int count = 0;
        
        for(Joueur joueur: l_joueur) {
            if(joueur != null) {
                count++;
            }
        }
        
        return count;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setJoueur1(Joueur joueur) {
        l_joueur[0] = joueur;
    }
    
    public void setJoueur2(Joueur joueur) {
        l_joueur[1] = joueur;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="???">

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Echiquier)) {
            return false;
        }
        Echiquier other = (Echiquier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Echiquier[ id=" + id + " ]";
    }
    
    //</editor-fold>
    
}
