package piece;

import chessgameclient.CaseGUI;
import chessgameclient.GameGUI;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Gére un roi
 * @author T4g1
 */
public class Roi extends Piece {
    private static final List<Point> correctMoves = new ArrayList<Point>();
    static {
        correctMoves.add(new Point( 1,  1));
        correctMoves.add(new Point(-1, -1));
        correctMoves.add(new Point( 1, -1));
        correctMoves.add(new Point(-1,  1));
        correctMoves.add(new Point( 0,  1));
        correctMoves.add(new Point( 0, -1));
        correctMoves.add(new Point( 1,  0));
        correctMoves.add(new Point(-1,  0));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Constructeur">
    
    /**
     * Constructeur du roi
     * 
     * @param x         Absice de la piéce
     * @param y         Ordonnée de la piéce
     * @param equipe    Equipé de la piéce
     */
    public Roi(int x, int y, Piece.Equipe equipe)
    {
        super(x, y, equipe);
    }
    
    //</editor-fold>
    
    @Override
    public List<Point> whereCanItGo(CaseGUI[][] l_case)
    {
        List<Point> l_where = new ArrayList<Point>();
        
        for(Point p: correctMoves) {
            int dx = (int)p.getX();
            int dy = (int)p.getY();
            
            int x = getX() + dx;
            int y = getY() + dy;
            
            if (x >= 0 && y >= 0 &&
                x < GameGUI.GRID_WIDTH && y < GameGUI.GRID_HEIGHT &&
                   (l_case[x][y].getPiece() == null ||
                    l_case[x][y].getPiece().getEquipe() != getEquipe())
                )
            {
                l_where.add(new Point(x, y));
            }
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
            return "7e.gif";
        }
        else {
            return "0e.gif";
        }
    }
}
