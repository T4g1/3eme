/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Chess.ChessCase;
import Chess.Constant;
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
public class Reine extends Piece implements Serializable {
    private static final List<Point> l_pattern = new ArrayList<>();
    static {
        l_pattern.add(new Point( 1,  1));
        l_pattern.add(new Point(-1, -1));
        l_pattern.add(new Point( 1, -1));
        l_pattern.add(new Point(-1,  1));
        l_pattern.add(new Point( 0,  1));
        l_pattern.add(new Point( 0, -1));
        l_pattern.add(new Point( 1,  0));
        l_pattern.add(new Point(-1,  0));
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //<editor-fold defaultstate="collapsed" desc="Constructeur">

    public Reine() {
    }
    
    /**
     * Constructeur du fou
     * 
     * @param x         Absice de la piece
     * @param y         Ordonnée de la piéce
     * @param color     Couleur de la piece
     */
    public Reine(int x, int y, Color color)
    {
        super(x, y, color);
    }
    
    //</editor-fold>
    
    /**
     * Donne les déplacements possible de la piéce
     * @param l_case        Liste des cases
     * @return              Liste des position possible
     */
    @Override
    public List<Point> whereCanItGo(ChessCase[][] l_case) {
        List<Point> l_where = new ArrayList<>();
        
        for(Point pattern: l_pattern) {
            int dx = (int)pattern.getX();
            int dy = (int)pattern.getY();
            
            int x = getPieceX() + dx;
            int y = getPieceY() + dy;
            while(  x >= 0 && y >= 0 &&
                    x < Constant.GRID_WIDTH && y < Constant.GRID_HEIGHT &&
                       (l_case[x][y].getPiece() == null ||
                        l_case[x][y].getPiece().getColor().getRGB() != getColor().getRGB())
                 )
            {
                l_where.add(new Point(x, y));
                
                // Piéce ennemie sur le chemin
                if (l_case[x][y].getPiece() != null &&
                    l_case[x][y].getPiece().getColor().getRGB() != getColor().getRGB())
                {
                    break; 
                }
                
                x += dx;
                y += dy;
            }
        }
        
        return l_where;
    }
    
    /**
     * Donne le nom de fichier de la piéce
     * @return              Nom du fichier contenant la piéce
     */
    @Override
    public String getFilename()
    {
        if(color.getRGB() == Color.WHITE.getRGB()) {
            return "7d.gif";
        }
        else {
            return "0d.gif";
        }
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
        if (!(object instanceof Reine)) {
            return false;
        }
        Reine other = (Reine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Reine[ id=" + id + " ]";
    }
    
}
