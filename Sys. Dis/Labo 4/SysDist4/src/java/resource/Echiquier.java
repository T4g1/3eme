package resource;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Echiquier - généré
 */
@Entity
@Table(name = "echiquier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Echiquier.findAll", query = "SELECT e FROM Echiquier e"),
    @NamedQuery(name = "Echiquier.findById", query = "SELECT e FROM Echiquier e WHERE e.id = :id"),
    @NamedQuery(name = "Echiquier.findByFocusedplayer", query = "SELECT e FROM Echiquier e WHERE e.focusedplayer = :focusedplayer"),
    @NamedQuery(name = "Echiquier.findByGameover", query = "SELECT e FROM Echiquier e WHERE e.gameover = :gameover"),
    @NamedQuery(name = "Echiquier.findByNom", query = "SELECT e FROM Echiquier e WHERE e.nom = :nom")})
public class Echiquier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "FOCUSEDPLAYER")
    private Integer focusedplayer;
    @Column(name = "GAMEOVER")
    private Boolean gameover;
    @Size(max = 255)
    @Column(name = "NOM")
    private String nom;
    @JoinTable(name = "echiquier_piece", joinColumns = {
        @JoinColumn(name = "Echiquier_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "l_pieces_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Piece> pieceCollection;
    @JoinColumn(name = "JNOIR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Joueur jnoirId;
    @JoinColumn(name = "JBLANC_ID", referencedColumnName = "ID")
    @ManyToOne
    private Joueur jblancId;
    @JoinColumn(name = "GAGNANT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Joueur gagnantId;

    public Echiquier() {
    }

    public Echiquier(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFocusedplayer() {
        return focusedplayer;
    }

    public void setFocusedplayer(Integer focusedplayer) {
        this.focusedplayer = focusedplayer;
    }

    public Boolean getGameover() {
        return gameover;
    }

    public void setGameover(Boolean gameover) {
        this.gameover = gameover;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public Collection<Piece> getPieceCollection() {
        return pieceCollection;
    }

    public void setPieceCollection(Collection<Piece> pieceCollection) {
        this.pieceCollection = pieceCollection;
    }

    public Joueur getJnoirId() {
        return jnoirId;
    }

    public void setJnoirId(Joueur jnoirId) {
        this.jnoirId = jnoirId;
    }

    public Joueur getJblancId() {
        return jblancId;
    }

    public void setJblancId(Joueur jblancId) {
        this.jblancId = jblancId;
    }

    public Joueur getGagnantId() {
        return gagnantId;
    }

    public void setGagnantId(Joueur gagnantId) {
        this.gagnantId = gagnantId;
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
        return "resource.Echiquier[ id=" + id + " ]";
    }
    
}
