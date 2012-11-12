/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Color;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author T4g1
 */
@Entity
public class Pion extends Piece implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Pion() {
    }
    
    /**
     * Constructeur du pion
     * 
     * @param x         Absice de la piéce
     * @param y         Ordonnée de la piéce
     * @param equipe    Equipé du pion
     */
    public Pion(int x, int y, Color color)
    {
        super(x, y, color);
    }
    
    /**
     * Donne le nom de fichier de la piéce
     * 
     * @param equipe        Couleur de la piéce
     * @return              Nom du fichier contenant la piéce
     */
    @Override
    public String getFilename(Color color)
    {
        if(color == Color.WHITE) {
            return "7a.gif";
        }
        else {
            return "0a.gif";
        }
    }

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
        if (!(object instanceof Pion)) {
            return false;
        }
        Pion other = (Pion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Pion[ id=" + id + " ]";
    }
    
    //</editor-fold>
    
}
