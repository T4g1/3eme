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
public class Joueur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Color color;

    //<editor-fold defaultstate="collapsed" desc="Accesseurs">

    public Long getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setColor(Color color) {
        this.color = color;
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
        if (!(object instanceof Joueur)) {
            return false;
        }
        Joueur other = (Joueur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Joueur[ id=" + id + " ]";
    }
    
    //</editor-fold>
    
}
