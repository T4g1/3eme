/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Chess.ChessCase;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    protected Color color;
    
    //<editor-fold defaultstate="collapsed" desc="Constructeur">

    public Piece() {
    }
    
    /**
     * Constructeur de la piéce
     * 
     * @param x     Absicce de la piéce
     * @param y     Ordonnée de la piéce
     * @param color Couleur de la piéce
     */
    public Piece(int x, int y, Color color)
    {
        this.x = x;
        this.y = y;
        
        this.color = color;
    }
    
    //</editor-fold>
    
    /**
     * Indique si la piéce est a la position donnée
     * @param x     Absicce
     * @param y     Ordonnée
     * @return      true si la piéce y est, false sinon
     */
    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }
    
    /**
     * Donne les déplacements possible de la piéce
     * @param l_pieces      Liste de piéces présente
     * @return              Liste des position possible
     */
    public List<Point> whereCanItGo(ChessCase[][] l_case) {
        return new ArrayList<>();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Accesseurs">

    /**
     * Donne le type de la piece
     * @return      Type de la piece
     */
    public String getType() {
        return "";
    }
    
    public Long getId() {
        return id;
    }
    
    /**
     * Donne la couleur de la piéce
     * 
     * @return      Couleur de la piéce 
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * Donne la position de la case en x
     * @return      Position de la case en x
     */
    public int getPieceX() {
        return x;
    }

    /**
     * Donne la position de la case en y
     * @return      Position de la case en y
     */
    public int getPieceY() {
        return y;
    }
    
    /**
     * Donne le nom de fichier de la piéce
     * @return              Nom du fichier contenant la piéce
     */
    public String getFilename()
    {
        if(color.getRGB() == Color.WHITE.getRGB()) {
            return "6a.gif";
        }
        else {
            return "6a.gif";
        }
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
