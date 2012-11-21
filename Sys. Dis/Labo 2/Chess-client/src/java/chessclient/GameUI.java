package chessclient;

import Chess.Constant;
import Entity.Piece;
import JMS.JMSConsumer;
import Session.GameSessionRemote;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Fenetre de jeu du jeu
 * @author T4g1
 */
public class GameUI extends javax.swing.JFrame implements MessageListener {
    private GameSessionRemote gameSession;
    private ChessCase[][] l_case = new ChessCase[Constant.GRID_WIDTH][Constant.GRID_HEIGHT];
    private Long echiquierId = -1L;
    private Long joueurId = -1L;
    
    private Collection<Piece> l_piece;
    private Piece selectedPiece;
    private List<Point> authorizedMove;
    private Boolean isMyTurn = false;
    
    private JMSConsumer consumer;
    
    /**
     * Creates new form GameUI
     */
    public GameUI() {
        gameSession = lookupGameSessionRemote();
        
        initComponents();
        
        selectedPiece = null;
        
        // Cree toutes les cases du jeu
        for(int y=0; y<Constant.GRID_HEIGHT; y++)
        {
            for(int x=0; x<Constant.GRID_WIDTH; x++)
            {
                // Cree la case
                if((x + y) % 2 == 0)
                {
                    l_case[x][y] = new ChessCase(this, x, y, Color.WHITE);
                }
                else
                {
                    l_case[x][y] = new ChessCase(this, x, y, Color.BLACK);
                }
        
                // Ajoute la case sur l'echequier
                echiquier.add(l_case[x][y]);
            }
        }
        
        setResizable(false);
    }

    /**
     * Permet a une case de prevenir qu'on a clicke dessus
     * 
     * @param case          Case clickee
     */
    public void onClic(ChessCase _case)
    {
        // Coordonée de la piéce en fonction de la couleur
        int x = _case.getCaseX();
        int y = _case.getCaseY();
        if(gameSession.getPlayerColor(joueurId).getRGB() == Color.BLACK.getRGB()) {
            y = (Constant.GRID_HEIGHT - 1) - y;
        }
        
        if(!isMyTurn) {
            return;
        }
        
        // Premier clic
        if(selectedPiece == null) {
            selectedPiece = gameSession.pieceOnEchiquierAt(echiquierId, x, y);
            if(selectedPiece == null) {
                return;
            }
            
            // Pas notre couleur
            if(selectedPiece.getColor().getRGB() != gameSession.getPlayerColor(joueurId).getRGB()) {
                selectedPiece = null;
                return;
            }
            
            authorizedMove = gameSession.whereCanItGo(echiquierId, selectedPiece.getId());
            switchHighlightedCase(true);
        }
        else {
            // Si on peut s'y rendre
            if(_case.isHighlighted()) {
                int result = gameSession.moveTo(
                        echiquierId, selectedPiece.getId(), x, y);
                
                if(result == -1) {
                    JOptionPane.showMessageDialog(
                            new JFrame(), "Deplacement echoué ...",
                            "Position interdite",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            
                switchHighlightedCase(false);
                selectedPiece = null;
            } else {
                switchHighlightedCase(false);
                
                selectedPiece = null;
                
                onClic(_case);
            }
        }
    }
    
    /**
     * Eteint les cases mise en évidence
     */
    private void switchHighlightedCase(Boolean value)
    {
        // Eteint les cases
        for(Point p: authorizedMove) {
            if(gameSession.getPlayerColor(joueurId).getRGB() == Color.BLACK.getRGB()) {
                l_case[(int)p.getX()][(Constant.GRID_HEIGHT - 1) - (int)p.getY()].highlight(value);
            }
            else {
                l_case[(int)p.getX()][(int)p.getY()].highlight(value);
            }
        }
        
        // Si on les a eteins, on vide la liste
        if(!value) {
            authorizedMove = new ArrayList<Point>();
        }
    }
    
    /**
     * Donne les infos pour lancer la partie
     * @param echiquierId       Id de l'echiquier
     * @param joueurId          Id du joueur
     */
    public void initGame(Long echiquierId, Long joueurId) {
        this.echiquierId = echiquierId;
        this.joueurId = joueurId;
        
        consumer = new JMSConsumer(String.valueOf(joueurId));
        consumer.addListener(this);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelGameState = new javax.swing.JLabel();
        echiquier = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chess GAME");

        labelGameState.setText("En attente de joueurs ...");

        echiquier.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        echiquier.setMinimumSize(new java.awt.Dimension(ChessCase.CASE_WIDTH * Constant.GRID_WIDTH, ChessCase.CASE_HEIGHT * Constant.GRID_HEIGHT));
        echiquier.setLayout(new java.awt.GridLayout(Constant.GRID_HEIGHT, Constant.GRID_WIDTH));

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

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        String text = "";
        
        try {
            text = textMessage.getText();
        } catch (JMSException ex) {
            Logger.getLogger(GameUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(text.equals("YOU_WON")) {
            labelGameState.setText("Vous avez gagné !");
            labelGameState.invalidate();
        }
        else if(text.equals("YOU_LOOSE")) {
            labelGameState.setText("Vous avez perdu !");
            labelGameState.invalidate();
        }
        else {
            refreshGameState();
            refreshEchiquier();
        }
    }
    
    /**
     * Met a jour le message indiquant l'etat de la partie
     */
    private void refreshGameState() {
        Long focusedId = gameSession.whosTurnIsIt(echiquierId);
        isMyTurn = (focusedId.equals(joueurId));
        
        if(isMyTurn) {
            labelGameState.setText("A vous de jouer ...");
        }
        else {
            labelGameState.setText("L'adversaire joue ...");
        }
        
        labelGameState.invalidate();
    }
    
    /**
     * Met a jour l'echiquier
     */
    private void refreshEchiquier() {
        // Demande la liste des pieces au serveur
        l_piece = gameSession.getListPieces(echiquierId);
        
        // Cree toutes les cases du jeu
        for(int y=0; y<Constant.GRID_HEIGHT; y++)
        {
            for(int x=0; x<Constant.GRID_WIDTH; x++)
            {
                l_case[x][y].removePiece();
            }
        }
        
        // Ajoute les piece sur leurs position de depart
        for(Piece piece: l_piece) {
            int x = piece.getPieceX();
            int y = piece.getPieceY();
            
            // On inverse le plateau pour l'un des joueurs
            if(gameSession.getPlayerColor(joueurId).getRGB() == Color.BLACK.getRGB()) {
                y = (Constant.GRID_HEIGHT - 1) - y;
            }
            
            l_case[x][y].addPiece(piece);
        }
        
        // Redessine l'echiquier dans la fenetre
        echiquier.invalidate();
    }
}
