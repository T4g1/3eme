package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Représente un compte client dans la BDD
 * @author T4g1
 */
@Entity
public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int solde;
    
    @ManyToOne(fetch=FetchType.EAGER)
    private Client proprietaire;
    
    //<editor-fold defaultstate="collapsed" desc="Default">

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Compte[ id=" + id + " ]";
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Accesseurs">
    
    public int getSolde()
    {
        return solde;
    }
    
    public Client getProprietaire()
    {
        return proprietaire;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    public void setSolde(int solde)
    {
        this.solde = solde;
    }
    
    public void setProprietaire(Client client)
    {
        this.proprietaire = client;
    }
    
    //</editor-fold>
}
