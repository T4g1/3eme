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
 * Gére un echiquier, représente une partie
 * @author T4g1
 */
@Entity
public class Echiquier implements Serializable {
    public static final int GRID_WIDTH = 8;
    public static final int GRID_HEIGHT = 8;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Case[][] l_case = new Case[GRID_WIDTH][GRID_HEIGHT];

    //<editor-fold defaultstate="collapsed" desc="Accesseur">
    
    public Long getId() {
        return id;
    }
    
    public Case getCase(int x, int y) {
        return l_case[x][y];
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    public void setId(Long id) {
        this.id = id;
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
