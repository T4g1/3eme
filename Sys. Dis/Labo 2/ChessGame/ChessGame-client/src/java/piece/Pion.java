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
        return new ArrayList<Point>();
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
