package piece;

import chessgameclient.CaseGUI;
import chessgameclient.GameGUI;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface pour une piéce de l'échiquier
 * @author T4g1
 */
public class Piece {
    public static enum Equipe { BLANC, NOIR };
    
    private int x;
    private int y;
    private Equipe equipe;
    
    //<editor-fold defaultstate="collapsed" desc="Constructeur">
    
    /**
     * Constructeur de la piéce
     * 
     * @param x     Absicce de la piéce
     * @param y     Ordonnée de la piéce
     */
    public Piece(int x, int y, Equipe equipe)
    {
        this.x = x;
        this.y = y;
        
        this.equipe = equipe;
    }
    
    //</editor-fold>
    
    /**
     * Indique ou la piéce peut se rendre
     * 
     * @param l_case    Liste des cases de l'échiquier
     * @return          Liste des cases sur lesquelles
     *                  on peut se rendre
     */
    public List<Point> whereCanItGo(CaseGUI[][] l_case)
    {
        return new ArrayList<Point>();
    }
    
    /**
     * Place la piéce sur l'échiquier
     * 
     * @param l_case        Liste des cases de l'échiquier 
     */
    public void putOnEchiquier(CaseGUI[][] l_case)
    {
        l_case[x][y].addPiece(this);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Accesseur">
    
    /**
     * Donne l'équipe de la piéce
     * 
     * @return      Equipe de la piéce 
     */
    public Equipe getEquipe()
    {
        return equipe;
    }
    
    /**
     * Donne l'absicce de la piéce
     * 
     * @return      Absicce de la piéce 
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Donne l'ordonnée de la piéce
     * 
     * @return Ordonnée de la piéce
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * Donne le nom de fichier de la piéce
     * 
     * @param equipe        Couleur de la piéce
     * @return              Nom du fichier contenant la piéce
     */
    public String getFilename(Equipe equipe)
    {
        if(equipe == Equipe.BLANC) {
            return "6a.gif";
        }
        else {
            return "6a.gif";
        }
    }
    
    //</editor-fold>
}
