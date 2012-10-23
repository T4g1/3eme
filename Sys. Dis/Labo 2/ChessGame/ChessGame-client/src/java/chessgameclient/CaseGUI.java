package chessgameclient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import piece.Piece;

/**
 * Gére une case du plateau de jeu
 * @author T4g1
 */
public class CaseGUI extends JPanel {
    private static final int CASE_WIDTH = 50;
    private static final int CASE_HEIGHT = 71;

    private GameGUI parent;
    private int x;
    private int y;
    private Piece piece;
    
    //<editor-fold defaultstate="collapsed" desc="Constructeurs">
    
    /**
     * Initialise une case en lui donnant sa position
     * 
     * @param x     Position en x de la case
     * @param y     Position en y de la case
     */
    public CaseGUI(GameGUI parent, int x, int y)
    {
        this.parent = parent;
        
        this.x = x;
        this.y = y;
        this.piece = null;
        
        // Ajoute un mouse listenner pour les clic utilisateurs
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onClic();
            }
        });
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
     * Retire les piéces sur la case
     */
    public void removePiece()
    {
        removeAll();
        piece = null;
    }
    
    /**
     * Ajoute une piéce sur la case
     * 
     * @param piece     Ajoute la piéce sur la case
     */
    public void addPiece(Piece piece)
    {
        add(new JLabel(new ImageIcon(
                "C:\\Users\\T4g1\\Desktop\\3eme\\Sys. Dis\\Labo 2\\" +
                "piecesimages\\" + piece.getFilename(piece.getEquipe())
        )));
        
        this.piece = piece;
    }
    
    /**
     * Fonction appellée lorsqu'on clique sur la case
     */
    public void onClic()
    {
        parent.onClic(this);
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

    /**
     * Donne le type de piece qui est sur la case
     * 
     * @return      Type de la piéce qui est sur la case
     */
    public Piece getPiece() {
        return piece;
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
