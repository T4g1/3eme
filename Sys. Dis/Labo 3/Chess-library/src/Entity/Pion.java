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
public class Pion extends Piece implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Pion() {
    }
    
    /**
     * Constructeur du pion
     * 
     * @param x         Absice de la piéce
     * @param y         Ordonnée de la piéce
     * @param equipe    Equipé du pion
     */
    public Pion(int x, int y, Color color)
    {
        super(x, y, color);
    }
    
    /**
     * Donne les déplacements possible de la piéce
     * @param l_case        Liste des cases
     * @return              Liste des position possible
     */
    @Override
    public List<Point> whereCanItGo(ChessCase[][] l_case) {
        List<Point> l_where = new ArrayList<>();
        
        if(getColor().getRGB() == Color.WHITE.getRGB()) {
            // Case devant lui
            if (getPieceY() >= 1 &&
                l_case[getPieceX()][getPieceY() - 1].getPiece() == null)
            {
                l_where.add(new Point(getPieceX(), getPieceY() - 1));
            }

            // Double avance: Si pas de piéce devant et en position de base
            if (getPieceY() == 6 &&
                l_case[getPieceX()][getPieceY() - 1].getPiece() == null &&
                l_case[getPieceX()][getPieceY() - 2].getPiece() == null)
            {
                l_where.add(new Point(getPieceX(), getPieceY() - 2));
            }

            // Prise de piéce en diagonale gauche
            if (getPieceX() >= 1 && getPieceY() >= 1 &&
                l_case[getPieceX() - 1][getPieceY() - 1].getPiece() != null &&
                l_case[getPieceX() - 1][getPieceY() - 1].getPiece().getColor().getRGB() != getColor().getRGB())
            {
                l_where.add(new Point(getPieceX() - 1, getPieceY() - 1));
            }

            // Prise de piéce en diagonale droite
            if (getPieceX() <= 6 && getPieceY() >= 1 &&
                l_case[getPieceX() + 1][getPieceY() - 1].getPiece() != null &&
                l_case[getPieceX() + 1][getPieceY() - 1].getPiece().getColor().getRGB() != getColor().getRGB())
            {
                l_where.add(new Point(getPieceX() + 1, getPieceY() - 1));
            }
        }
        else {
            // Case devant lui
            if (getPieceY() <= 6 &&
                l_case[getPieceX()][getPieceY() + 1].getPiece() == null)
            {
                l_where.add(new Point(getPieceX(), getPieceY() + 1));
            }

            // Double avance: Si pas de piéce devant et en position de base
            if (getPieceY() == 1 &&
                l_case[getPieceX()][getPieceY() + 1].getPiece() == null &&
                l_case[getPieceX()][getPieceY() + 2].getPiece() == null)
            {
                l_where.add(new Point(getPieceX(), getPieceY() + 2));
            }

            // Prise de piéce en diagonale gauche
            if (getPieceX() >= 1 && getPieceY() <= 6 &&
                l_case[getPieceX() - 1][getPieceY() + 1].getPiece() != null &&
                l_case[getPieceX() - 1][getPieceY() + 1].getPiece().getColor().getRGB() != getColor().getRGB())
            {
                l_where.add(new Point(getPieceX() - 1, getPieceY() + 1));
            }

            // Prise de piéce en diagonale droite
            if (getPieceX() <= 6 && getPieceY() <= 6 &&
                l_case[getPieceX() + 1][getPieceY() + 1].getPiece() != null &&
                l_case[getPieceX() + 1][getPieceY() + 1].getPiece().getColor().getRGB() != getColor().getRGB())
            {
                l_where.add(new Point(getPieceX() + 1, getPieceY() + 1));
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
            return "6a.gif";
        }
        else {
            return "1a.gif";
        }
    }

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
        if (!(object instanceof Pion)) {
            return false;
        }
        Pion other = (Pion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Pion[ id=" + id + " ]";
    }
    
    //</editor-fold>
    
}
