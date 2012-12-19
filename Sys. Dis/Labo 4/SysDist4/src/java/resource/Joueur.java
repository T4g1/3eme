package resource;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Joueur - généré
 */
@Entity
@Table(name = "joueur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Joueur.findAll", query = "SELECT j FROM Joueur j"),
    @NamedQuery(name = "Joueur.findById", query = "SELECT j FROM Joueur j WHERE j.id = :id"),
    @NamedQuery(name = "Joueur.findByPseudo", query = "SELECT j FROM Joueur j WHERE j.pseudo = :pseudo")})
public class Joueur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "PSEUDO")
    private String pseudo;
    @OneToMany(mappedBy = "jnoirId")
    private Collection<Echiquier> echiquierCollection;
    @OneToMany(mappedBy = "jblancId")
    private Collection<Echiquier> echiquierCollection1;
    @OneToMany(mappedBy = "gagnantId")
    private Collection<Echiquier> echiquierCollection2;

    public Joueur() {
    }

    public Joueur(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @XmlTransient
    public Collection<Echiquier> getEchiquierCollection() {
        return echiquierCollection;
    }

    public void setEchiquierCollection(Collection<Echiquier> echiquierCollection) {
        this.echiquierCollection = echiquierCollection;
    }

    @XmlTransient
    public Collection<Echiquier> getEchiquierCollection1() {
        return echiquierCollection1;
    }

    public void setEchiquierCollection1(Collection<Echiquier> echiquierCollection1) {
        this.echiquierCollection1 = echiquierCollection1;
    }

    @XmlTransient
    public Collection<Echiquier> getEchiquierCollection2() {
        return echiquierCollection2;
    }

    public void setEchiquierCollection2(Collection<Echiquier> echiquierCollection2) {
        this.echiquierCollection2 = echiquierCollection2;
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
        return "resource.Joueur[ id=" + id + " ]";
    }
    
}
