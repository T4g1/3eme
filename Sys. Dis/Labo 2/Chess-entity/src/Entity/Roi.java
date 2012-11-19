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
public class Roi extends Piece implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //<editor-fold defaultstate="collapsed" desc="Constructeur">

    public Roi() {
    }
    
    /**
     * Constructeur du fou
     * 
     * @param x         Absice de la piece
     * @param y         Ordonnée de la piéce
     * @param color     Couleur de la piece
     */
    public Roi(int x, int y, Color color)
    {
        super(x, y, color);
    }
    
    //</editor-fold>
    
    /**
     * Donne le nom de fichier de la piéce
     * @return              Nom du fichier contenant la piéce
     */
    @Override
    public String getFilename()
    {
        if(color.getRGB() == Color.WHITE.getRGB()) {
            return "7e.gif";
        }
        else {
            return "0e.gif";
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roi)) {
            return false;
        }
        Roi other = (Roi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Roi[ id=" + id + " ]";
    }
    
}
