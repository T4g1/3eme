/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chessclient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * Fenetre de jeu du jeu
 * @author T4g1
 */
public class GameUI extends javax.swing.JFrame {
    public static final int GRID_WIDTH = 8;
    public static final int GRID_HEIGHT = 8;
    private static final int CASE_WIDTH = 50;
    private static final int CASE_HEIGHT = 71;
    private static final Color highlightColor = Color.RED;
    
    private ChessCase[][] l_case = new ChessCase[GRID_WIDTH][GRID_HEIGHT];
    
    //private List<Piece> l_piece;
    private ChessCase selectedCase;
    //private List<Point> highlighted;
    
    /**
     * Creates new form GameUI
     */
    public GameUI() {
        initComponents();
        
        selectedCase = null;
        
        // Crée toutes les cases du jeu
        for(int y=0; y<GRID_HEIGHT; y++)
        {
            for(int x=0; x<GRID_WIDTH; x++)
            {
                // Crée la case
                if((x + y) % 2 == 0)
                {
                    l_case[x][y] = new ChessCase(this, x, y, Color.WHITE);
                }
                else
                {
                    l_case[x][y] = new ChessCase(this, x, y, Color.BLACK);
                }
        
                // Ajoute la case sur l'échéquier
                echiquier.add(l_case[x][y]);
            }
        }
        
        echiquier.invalidate();
        // Ajoute l'échiquier dans la fenétre
        //add(echiquier);
        
        // Modifie les dimensions de la fenétre
        Dimension window_size = new Dimension();
        window_size.width = GRID_WIDTH * ChessCase.getCaseWidth();
        window_size.height = GRID_HEIGHT * ChessCase.getCaseHeight();
        
        setSize(window_size);
        setResizable(false);
        
        // Liste des piéces en jeu
        /*l_piece = new ArrayList<Piece>();
        
        l_piece.add(new Tour    (0, 0, Equipe.NOIR));
        l_piece.add(new Cavalier(1, 0, Equipe.NOIR));
        l_piece.add(new Fou     (2, 0, Equipe.NOIR));
        l_piece.add(new Reine   (3, 0, Equipe.NOIR));
        l_piece.add(new Roi     (4, 0, Equipe.NOIR));
        l_piece.add(new Fou     (5, 0, Equipe.NOIR));
        l_piece.add(new Cavalier(6, 0, Equipe.NOIR));
        l_piece.add(new Tour    (7, 0, Equipe.NOIR));
        l_piece.add(new Pion    (0, 1, Equipe.NOIR));
        l_piece.add(new Pion    (1, 1, Equipe.NOIR));
        l_piece.add(new Pion    (2, 1, Equipe.NOIR));
        l_piece.add(new Pion    (3, 1, Equipe.NOIR));
        l_piece.add(new Pion    (4, 1, Equipe.NOIR));
        l_piece.add(new Pion    (5, 1, Equipe.NOIR));
        l_piece.add(new Pion    (6, 1, Equipe.NOIR));
        l_piece.add(new Pion    (7, 1, Equipe.NOIR));
        
        l_piece.add(new Tour    (0, 7, Equipe.BLANC));
        l_piece.add(new Cavalier(1, 7, Equipe.BLANC));
        l_piece.add(new Fou     (2, 7, Equipe.BLANC));
        l_piece.add(new Reine   (3, 7, Equipe.BLANC));
        l_piece.add(new Roi     (4, 7, Equipe.BLANC));
        l_piece.add(new Fou     (5, 7, Equipe.BLANC));
        l_piece.add(new Cavalier(6, 7, Equipe.BLANC));
        l_piece.add(new Tour    (7, 7, Equipe.BLANC));
        l_piece.add(new Pion    (0, 6, Equipe.BLANC));
        l_piece.add(new Pion    (1, 6, Equipe.BLANC));
        l_piece.add(new Pion    (2, 6, Equipe.BLANC));
        l_piece.add(new Pion    (3, 6, Equipe.BLANC));
        l_piece.add(new Pion    (4, 6, Equipe.BLANC));
        l_piece.add(new Pion    (5, 6, Equipe.BLANC));
        l_piece.add(new Pion    (6, 6, Equipe.BLANC));
        l_piece.add(new Pion    (7, 6, Equipe.BLANC));
        
        // Ajoute les piéce sur leurs position de départ
        for(Piece piece: l_piece) {
            l_case[piece.getX()][piece.getY()].addPiece(piece);
        }*/
    }

    /**
     * Permet a une case de prévenir qu'on a clické dessus
     * 
     * @param case          Case clickée
     */
    public void onClic(ChessCase _case)
    {
        // Premier clic
        if(selectedCase == null) {
            /*if(_case.getPiece() != null) {
                selectedCase = _case;
                
                highlighted = _case.getPiece().whereCanItGo(l_case);
                for(Point p: highlighted) {
                    l_case[(int)p.getX()][(int)p.getY()].highlight(true);
                }
            }*/
        }
        else {
            // Si on peut s'y rendre
            /*if(_case.isHighlighted()) {
                moveTo(_case);
                
                shtudownHighlightedCase();
            } else {
                shtudownHighlightedCase();
                
                selectedCase = null;
                
                onClic(_case);
            }*/
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelGameState = new javax.swing.JLabel();
        echiquier = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelGameState.setText("En attente de joueurs ...");

        echiquier.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        echiquier.setLayout(new java.awt.GridLayout(GRID_HEIGHT, GRID_WIDTH));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(echiquier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelGameState)
                        .addGap(0, 260, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelGameState)
                .addGap(18, 18, 18)
                .addComponent(echiquier, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel echiquier;
    private javax.swing.JLabel labelGameState;
    // End of variables declaration//GEN-END:variables
}
