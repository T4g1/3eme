package chessclient;

import Entity.Piece;
import Session.GameSessionRemote;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Fenetre de jeu du jeu
 * @author T4g1
 */
public class GameUI extends javax.swing.JFrame {
    public static final int GRID_WIDTH = 8;
    public static final int GRID_HEIGHT = 8;
    
    private GameSessionRemote gameSession;
    private ChessCase[][] l_case = new ChessCase[GRID_WIDTH][GRID_HEIGHT];
    
    private Color playerColor = Color.WHITE;
    private List<Piece> l_piece;
    private ChessCase selectedCase;
    private List<Point> authorizedMove;
    
    /**
     * Creates new form GameUI
     */
    public GameUI() {
        gameSession = lookupGameSessionRemote();
        
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
        
        // Modifie les dimensions de la fenêtre
        Dimension window_size = new Dimension();
        window_size.width = GRID_WIDTH * ChessCase.getCaseWidth();
        window_size.height = GRID_HEIGHT * ChessCase.getCaseHeight();
        
        setSize(window_size);
        setResizable(false);
        
        // Demande la liste des piéces au serveur
        l_piece = gameSession.getListPieces();
        
        // Ajoute les piéce sur leurs position de départ
        for(Piece piece: l_piece) {
            int x = piece.getPieceX();
            int y = piece.getPieceY();
            
            // On inverse le plateau pour l'un des joueurs
            if(playerColor == Color.WHITE) {
                y = (GRID_HEIGHT - 1) - y;
            }
            
            l_case[x][y].addPiece(piece);
        }
        
        // Redessine l'échiquier dans la fenêtre
        echiquier.invalidate();
    }

    /**
     * Permet à une case de prévenir qu'on a clické dessus
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

    private GameSessionRemote lookupGameSessionRemote() {
        try {
            Context c = new InitialContext();
            return (GameSessionRemote) c.lookup("java:comp/env/GameSession");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
