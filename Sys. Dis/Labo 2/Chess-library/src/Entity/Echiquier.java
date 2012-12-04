package Entity;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Gére un echiquier, représente une partie
 * @author T4g1
 */
@Entity
public class Echiquier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private int focusedPlayer;
    private boolean gameOver;
    
    @OneToMany(fetch=FetchType.EAGER)
    private Collection<Piece> l_pieces = new ArrayList<Piece>();
    
    private Joueur[] l_joueur = new Joueur[2];
    
    public void init(String name) {
        setJoueur1(null);
        setJoueur2(null);
        
        setNom(name);
        gameOver = false;
        
        l_pieces.add(new Tour    (0, 0, Color.BLACK));
        l_pieces.add(new Cavalier(1, 0, Color.BLACK));
        l_pieces.add(new Fou     (2, 0, Color.BLACK));
        l_pieces.add(new Reine   (3, 0, Color.BLACK));
        l_pieces.add(new Roi     (4, 0, Color.BLACK));
        l_pieces.add(new Fou     (5, 0, Color.BLACK));
        l_pieces.add(new Cavalier(6, 0, Color.BLACK));
        l_pieces.add(new Tour    (7, 0, Color.BLACK));
        l_pieces.add(new Pion    (0, 1, Color.BLACK));
        l_pieces.add(new Pion    (1, 1, Color.BLACK));
        l_pieces.add(new Pion    (2, 1, Color.BLACK));
        l_pieces.add(new Pion    (3, 1, Color.BLACK));
        l_pieces.add(new Pion    (4, 1, Color.BLACK));
        l_pieces.add(new Pion    (5, 1, Color.BLACK));
        l_pieces.add(new Pion    (6, 1, Color.BLACK));
        l_pieces.add(new Pion    (7, 1, Color.BLACK));
        
        l_pieces.add(new Tour    (0, 7, Color.WHITE));
        l_pieces.add(new Cavalier(1, 7, Color.WHITE));
        l_pieces.add(new Fou     (2, 7, Color.WHITE));
        l_pieces.add(new Reine   (3, 7, Color.WHITE));
        l_pieces.add(new Roi     (4, 7, Color.WHITE));
        l_pieces.add(new Fou     (5, 7, Color.WHITE));
        l_pieces.add(new Cavalier(6, 7, Color.WHITE));
        l_pieces.add(new Tour    (7, 7, Color.WHITE));
        l_pieces.add(new Pion    (0, 6, Color.WHITE));
        l_pieces.add(new Pion    (1, 6, Color.WHITE));
        l_pieces.add(new Pion    (2, 6, Color.WHITE));
        l_pieces.add(new Pion    (3, 6, Color.WHITE));
        l_pieces.add(new Pion    (4, 6, Color.WHITE));
        l_pieces.add(new Pion    (5, 6, Color.WHITE));
        l_pieces.add(new Pion    (6, 6, Color.WHITE));
        l_pieces.add(new Pion    (7, 6, Color.WHITE));
        
        focusedPlayer = 0;
    }
    
    /**
     * Vide les piéces sur l'echiquier
     */
    public void clearEchiquier()
    {
        l_pieces.clear();
    }
    
    /**
     * Supprime la piéce à la position donnée
     * @param x     Absice
     * @param y     Ordonnée
     */
    public void removePiece(int x, int y) {
        for(Piece piece: l_pieces) {
            if(piece.isAt(x, y)) {
                l_pieces.remove(piece);
                break;
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Accesseurs">
    
    public Long getId() {
        return id;
    }
    
    /**
     * Donne la piéce à la position donnée
     * @param x     Absice de la piéce
     * @param y     Ordonnée de la piéce
     * @return      Piéce demandée ou null si erreur
     */
    public Piece getPiece(int x, int y) {
        for(Piece piece: l_pieces) {
            if(piece.isAt(x, y)) {
                return piece;
            }
        }
        
        return null;
    }
    
    public Collection<Piece> getListPiece() {
        return l_pieces;
    }
    
    public String getNom() {
        return nom;
    }
    
    public int getPlayerCount() {
        int count = 0;
        
        for(Joueur joueur: l_joueur) {
            if(joueur != null) {
                count++;
            }
        }
        
        return count;
    }
    
    public boolean gameOver() {
        return gameOver;
    }
    
    public Joueur getJoueur1() {
        return l_joueur[0];
    }
    
    public Joueur getJoueur2() {
        return l_joueur[1];
    }
    
    /**
     * Indique quel joueur peut jouer
     * @return      Id du joueur qui a la main
     */
    public Long getFocusedPlayer() {
        return l_joueur[focusedPlayer].getId();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setJoueur1(Joueur joueur) {
        l_joueur[0] = joueur;
    }
    
    public void setJoueur2(Joueur joueur) {
        l_joueur[1] = joueur;
    }
    
    /**
     * Change le joeuur qui a la main
     */
    public void switchFocusedPlayer() {
        focusedPlayer = (focusedPlayer + 1) % 2;
    }
    
    /**
     * Indique l'etat de la partie
     * @param value     Etat de la partie
     */
    public void setGameOver(boolean value) {
        gameOver = value;
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
