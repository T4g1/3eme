/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author T4g1
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "all", query = "SELECT j FROM Joueur j")
    //@NamedQuery(name = "HighScore", query = "select j.pseudo, count(e.id) from Joueur j , Echiquier e where j.id = e.gagnant group by j.id")
})
@XmlRootElement
public class Joueur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pseudo;

    //<editor-fold defaultstate="collapsed" desc="Accesseurs">

    public Long getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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
        return "Entity.Joueur[ id=" + id +" ]";
    }
    
    //</editor-fold>

    

    

    
    
}
