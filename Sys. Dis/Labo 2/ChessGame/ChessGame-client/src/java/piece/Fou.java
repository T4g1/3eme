package piece;

import chessgameclient.CaseGUI;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Gére un fou
 * @author T4g1
 */
public class Fou extends Piece {
    //<editor-fold defaultstate="collapsed" desc="Constructeur">
    
    /**
     * Constructeur du fou
     * 
     * @param x         Absice de la piéce
     * @param y         Ordonnée de la piéce
     * @param equipe    Equipé de la piéce
     */
    public Fou(int x, int y, Piece.Equipe equipe)
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
            return "7c.gif";
        }
        else {
            return "0c.gif";
        }
    }
}
