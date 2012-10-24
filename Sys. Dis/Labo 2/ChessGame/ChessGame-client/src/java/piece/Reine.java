package piece;

import chessgameclient.CaseGUI;
import chessgameclient.GameGUI;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Gére une reine
 * @author T4g1
 */
public class Reine extends Piece {
    private static final List<Point> l_pattern = new ArrayList<Point>();
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
    
    //<editor-fold defaultstate="collapsed" desc="Constructeur">
    
    /**
     * Constructeur de la reine
     * 
     * @param x         Absice de la piéce
     * @param y         Ordonnée de la piéce
     * @param equipe    Equipé de la piéce
     */
    public Reine(int x, int y, Piece.Equipe equipe)
    {
        super(x, y, equipe);
    }
    
    //</editor-fold>
    
    @Override
    public List<Point> whereCanItGo(CaseGUI[][] l_case)
    {
        List<Point> l_where = new ArrayList<Point>();
        
        for(Point pattern: l_pattern) {
            int dx = (int)pattern.getX();
            int dy = (int)pattern.getY();
            
            int x = getX() + dx;
            int y = getY() + dy;
            while(  x >= 0 && y >= 0 &&
                    x < GameGUI.GRID_WIDTH && y < GameGUI.GRID_HEIGHT &&
                       (l_case[x][y].getPiece() == null ||
                        l_case[x][y].getPiece().getEquipe() != getEquipe())
                 )
            {
                l_where.add(new Point(x, y));
                
                // Piéce ennemie sur le chemin
                if (l_case[x][y].getPiece() != null &&
                    l_case[x][y].getPiece().getEquipe() != getEquipe())
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
     * 
     * @param equipe        Couleur de la piéce
     * @return              Nom du fichier contenant la piéce
     */
    @Override
    public String getFilename(Equipe equipe)
    {
        if(equipe == Equipe.BLANC) {
            return "7d.gif";
        }
        else {
            return "0d.gif";
        }
    }
}
