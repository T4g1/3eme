/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgameclient;

import javax.swing.JPanel;

/**
 *
 * @author T4g1
 */
public class CaseGUI extends JPanel {
    private static final int CASE_WIDTH = 50;
    private static final int CASE_HEIGHT = 71;
    
    private int x;
    private int y;
    
    //<editor-fold defaultstate="collapsed" desc="Constructeurs">
    
    /**
     * Initialise une case en lui donnant sa position
     * 
     * @param x     Position en x de la case
     * @param y     Position en y de la case
     */
    public CaseGUI(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Initialise une case
     */
    public CaseGUI()
    {
        this.x = 0;
        this.y = 0;
    }
    
    //</editor-fold>
    
    /**
     * Ajoute une piéce sur la case
     * 
     * @return      True si l'ajout est ok, false sinon
     */
    public boolean addPiece()
    {
        //add(new JLabel(new ImageIcon(filename)));
        
        return true;
    }

    //<editor-fold defaultstate="collapsed" desc="Accesseurs">
    
    /**
     * Donne la position de la case en x
     * 
     * @return      Position de la case en x
     */
    public int getCaseX() {
        return x;
    }

    /**
     * Donne la position de la case en y
     * 
     * @return      Position de la case en y
     */
    public int getCaseY() {
        return y;
    }

    /**
     * Donne la largeur d'une case
     * 
     * @return      Largeur d'une case
     */
    public static int getCaseWidth() {
        return CASE_WIDTH;
    }

    /**
     * Donne la largeur d'une case
     * 
     * @return      Largeur d'une case
     */
    public static int getCaseHeight() {
        return CASE_HEIGHT;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    /**
     * Modifie la position de la case en x
     * 
     * @param x         Position de la case en x
     */
    public void setCaseX(int x) {
        this.x = x;
    }

    /**
     * Modifie la position de la case en y
     * 
     * @param y         Position de la case en y
     */
    public void setCaseY(int y) {
        this.y = y;
    }
    
    //</editor-fold>
}
