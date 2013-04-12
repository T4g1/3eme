package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Représente un client dans la BDD
 * @author T4g1
 */
@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private char[] pseudo;
    private char[] password;

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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Client[ id=" + id + " ]";
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Accesseurs">
    
    public boolean sameAsPseudo(String pseudo)
    {
        System.out.println("*" + this.pseudo + "*");
        System.out.println("*" + this.pseudo.toString() + "*");
        System.out.println("*" + pseudo + "*");
        System.out.println("*" + pseudo.toCharArray() + "*");
        System.out.println("*" + pseudo.contentEquals(this.pseudo.toString()) + "*");
            
        return false;
    }
    
    public boolean sameAsPassword(String password)
    {
        return true;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    public void setPseudo(String pseudo)
    {
        this.pseudo = pseudo.toCharArray();
    }
    
    public void setPassword(String password)
    {
        this.password = password.toCharArray();
    }
    
    //</editor-fold>
}
