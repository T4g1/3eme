package piece;

import chessgameclient.CaseGUI;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Gére un pion
 * @author T4g1
 */
public class Pion extends Piece {
    
    //<editor-fold defaultstate="collapsed" desc="Constructeur">
    
    /**
     * Constructeur du pion
     * 
     * @param x         Absice du pion
     * @param y         Ordonnée de la piéce
     * @param equipe    Equipé du pion
     */
    public Pion(int x, int y, Equipe equipe)
    {
        super(x, y, equipe);
    }
    
    //</editor-fold>
    
    @Override
    public List<Point> whereCanItGo(CaseGUI[][] l_case)
    {
        List<Point> l_where = new ArrayList<Point>();
        
        // Case devant lui
        if (getY() >= 1 &&
            l_case[getX()][getY() - 1].getPiece() == null)
        {
            l_where.add(new Point(getX(), getY() - 1));
        }
        
        // Double avance: Si pas de piéce devant et en position de base
        if (getY() == 6 &&
            l_case[getX()][getY() - 1].getPiece() == null &&
            l_case[getX()][getY() - 2].getPiece() == null)
        {
            l_where.add(new Point(getX(), getY() - 2));
        }
        
        // Prise de piéce en diagonale gauche
        if (getX() >= 1 && getY() >= 1 &&
            l_case[getX() - 1][getY() - 1].getPiece() != null &&
            l_case[getX() - 1][getY() - 1].getPiece().getEquipe() != getEquipe())
        {
            l_where.add(new Point(getX() - 1, getY() - 1));
        }
        
        // Prise de piéce en diagonale droite
        if (getX() <= 6 && getY() >= 1 &&
            l_case[getX() + 1][getY() - 1].getPiece() != null &&
            l_case[getX() + 1][getY() - 1].getPiece().getEquipe() != getEquipe())
        {
            l_where.add(new Point(getX() + 1, getY() - 1));
        }
        
        return l_where;
    }
    
    /**
     * Donne le nom de fichier de la piéce
     * 
     * @param equipe        Couleur de la piéce
     * @return              Nom du fichier contenant la piéce
     */
    @Override
    public String getFilename(Equipe equipe)
    {
        if(equipe == Equipe.BLANC) {
            return "6a.gif";
        }
        else {
            return "1a.gif";
        }
    }
}
