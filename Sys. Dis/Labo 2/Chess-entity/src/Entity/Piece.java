/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
public class Piece implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int x;
    private int y;
    
    /**
     * Indique si la piéce est a la position donnée
     * @param x     Absicce
     * @param y     Ordonnée
     * @return      true si la piéce y est, false sinon
     */
    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Accesseurs">

    public Long getId() {
        return id;
    }
    
    /**
     * Donne la position de la case en x
     * @return      Position de la case en x
     */
    public int getCaseX() {
        return x;
    }

    /**
     * Donne la position de la case en y
     * @return      Position de la case en y
     */
    public int getCaseY() {
        return y;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Modifie la position de la case en x
     * @param x         Position de la case en x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Modifie la position de la case en y
     * @param y         Position de la case en y
     */
    public void setY(int y) {
        this.y = y;
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
        if (!(object instanceof Piece)) {
            return false;
        }
        Piece other = (Piece) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Piece[ id=" + id + " ]";
    }
    
    //</editor-fold>
    
}
