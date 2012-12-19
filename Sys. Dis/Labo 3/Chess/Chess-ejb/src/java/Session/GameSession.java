package Session;

import Chess.ChessCase;
import Chess.Constant;
import Entity.Echiquier;
import Entity.Joueur;
import Entity.Piece;
import Facade.EchiquierFacadeLocal;
import Facade.JoueurFacadeLocal;
import Facade.PieceFacadeLocal;
import JMS.JMSProducer;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Fonction gérant la partie en cours
 * @author T4g1
 */
@Stateless
public class GameSession implements GameSessionRemote {
    @EJB
    private PieceFacadeLocal pieceFacade;
    @EJB
    private JoueurFacadeLocal joueurFacade;
    @EJB
    private EchiquierFacadeLocal echiquierFacade;
    
    /**
     * Donne la liste des piéces en jeu au joueur
     * @return  Liste de piéces et leurs coordonnées
     */
    @Override
    public Collection<Piece> getListPieces(Long id) {
        Echiquier echiquier = echiquierFacade.find(id);
        if(echiquier != null) {
            return echiquier.getListPiece();
        }
        else {
            return new ArrayList<Piece>();
        }
    }
    
    /**
     * Donne la piéce sur la case donée
     * @param echiquierId   Id de l'echiquier
     * @param x             Absice de la case
     * @param y             Ordonnée de la case
     * @return              Reference de la piece, null si case vide
     */
    @Override
    public Piece pieceOnEchiquierAt(Long echiquierId, int x, int y) {
        Echiquier echiquier = echiquierFacade.find(echiquierId);
        if(echiquier == null) {
            return null;
        }
        
        Piece piece = echiquier.getPiece(x, y);
        return piece;
    }
    
    /**
     * Donne la liste des position sur lesquelle peut aller la piece donnée
     * @param echiquierId    Id de l'echiquier
     * @param pieceId        Id de la piece a deplacer
     * @return              Liste de point
     */
    @Override
    public List<Point> whereCanItGo(Long echiquierId, Long pieceId) {
        Echiquier echiquier = echiquierFacade.find(echiquierId);
        if(echiquier == null) {
            return new ArrayList<Point>();
        }
        
        Piece pieceCible = pieceFacade.find(pieceId);
        if(pieceCible == null) {
            return new ArrayList<Point>();
        }
        
        Collection<Piece> l_pieces = echiquier.getListPiece();
        
        // Initialise le tableau de case
        ChessCase[][] l_case = new ChessCase[Constant.GRID_WIDTH][Constant.GRID_HEIGHT];
        for(int y=0; y<Constant.GRID_HEIGHT; y++)
        {
            for(int x=0; x<Constant.GRID_WIDTH; x++)
            {
                l_case[x][y] = new ChessCase();
            }
        }
        
        // Place les piéces dessus
        for(Piece piece: l_pieces) {
            int x = piece.getPieceX();
            int y = piece.getPieceY();
            
            l_case[x][y].setPiece(piece);
        }
        
        return pieceCible.whereCanItGo(l_case);
    }
    
    /**
     * Déplace la piéce donnée sur la case demandée
     * @param echiquierId       Id de l'echiquier
     * @param pieceId           Id de la piéce
     * @param x                 Absice de la case cible
     * @param y                 Ordonnée de la case cible
     * @return                  -1 deplacement non autorisé, 1 succes
     */
    @Override
    public int moveTo(Long echiquierId, Long pieceId, int x, int y) {
        Echiquier echiquier = echiquierFacade.find(echiquierId);
        
        // Destination
        List<Point> l_points = whereCanItGo(echiquierId, pieceId);
        Boolean canMove = false;
        for(Point point: l_points) {
            if(point.getX() == (double)x && point.getY() == (double)y) {
                canMove = true;
            }
        }
         // Déplacer interdit
        if(!canMove) {
            return -1;
        }
        
        // Piéce prise
        String type = "";
        Piece piecePrise = echiquier.getPiece(x, y);
        if(piecePrise != null) {
            type = piecePrise.getType();
            
            echiquier.removePiece(x, y);
            
            pieceFacade.remove(piecePrise);
        }
        
        // Modifie la piece
        Piece piece = pieceFacade.find(pieceId);
        piece.setX(x);
        piece.setY(y);
        pieceFacade.edit(piece);
        
        // Ce joueur a joué
        echiquier.switchFocusedPlayer();
        echiquierFacade.edit(echiquier);
        
        JMSProducer producer = new JMSProducer();
        
        // Si c'est le roi
        if(type.equals("ROI")) {
//            if(echiquier.getJoueur1().getColor().getRGB() == piecePrise.getColor().getRGB()) {
//                producer.sendMessage(echiquier.getJoueur1().getId(), "YOU_LOOSE");
//                producer.sendMessage(echiquier.getJoueur2().getId(), "YOU_WIN");
//            }
//            else {
//                producer.sendMessage(echiquier.getJoueur1().getId(), "YOU_WIN");
//                producer.sendMessage(echiquier.getJoueur2().getId(), "YOU_LOOSE");
//            }
            
            // Termine la partie
            echiquier.setGameOver(true);
            echiquierFacade.edit(echiquier);
        }
        else {
            // Previens qu'un tour est passé
            producer.sendMessage(echiquier.getJoueur1().getId(), "NEXT_TURN");
            producer.sendMessage(echiquier.getJoueur2().getId(), "NEXT_TURN");
        }
        
        producer.close();
        
        return 1;
    }
    
    /**
     * Termine une partie
     * @param echiquierId       Id de l'echiquier
     */
    private void deleteEchiquier(Long echiquierId) {
        Echiquier echiquier = echiquierFacade.find(echiquierId);
        if(echiquier == null) {
            return;
        }
        
        // Supprime les pieces
        Collection<Piece> l_pieces = echiquier.getListPiece();
        for(Piece piece: l_pieces) {
            pieceFacade.remove(piece);
        }
        echiquier.clearEchiquier();
        echiquierFacade.edit(echiquier);
        echiquierFacade.remove(echiquier);
    }
    
    /**
     * Indique l'id du joueur qui doit jouer
     * @param id        Id de la partie concernée
     * @return          Id du joueur qui a la main
     */
    @Override
    public Long whosTurnIsIt(Long id) {
        Echiquier echiquier = echiquierFacade.find(id);
        
        return echiquier.getFocusedPlayer();
    }
    
    /**
     * Indique qu'un joueur se tire d'une partie
     * @param joueurId          Id du joueur
     * @param echiquierId       Id de l'echiquier
     */
    @Override
    public void playerLeave(Long joueurId, Long echiquierId) {
        System.out.println("Player leave");
        Echiquier echiquier = echiquierFacade.find(echiquierId);
        if(echiquier == null) {
            return;
        }
        
        Joueur joueur1 = echiquier.getJoueur1();
        Joueur joueur2 = echiquier.getJoueur2();
        
        JMSProducer producer = new JMSProducer();
        
        // Previens l'autre joueur qu'il a gagne
        if(joueur1 != null && joueurId.equals(joueur1.getId())) {
            echiquier.setJoueur1(null);
            System.out.println("Delete player 1");
            
            // Si la partie n'est pas finie
            if(!echiquier.gameOver() && joueur2 != null) {
                producer.sendMessage(joueur2.getId(), "YOU_WIN");
            }
        }
        else {
            echiquier.setJoueur2(null);
            System.out.println("Delete player 2");
            
            // Si la partie n'est pas finie
            if(!echiquier.gameOver() && joueur1 != null) {
                producer.sendMessage(joueur1.getId(), "YOU_WIN");
            }
        }
        
        // Fin de la partie
        echiquier.setGameOver(true);
        echiquierFacade.edit(echiquier);
        
        // Les deux joueurs sont partit
        if(echiquier.getJoueur1() == null && echiquier.getJoueur2() == null) {
            deleteEchiquier(echiquierId);
            System.out.println("Delete echiquier");
        }
        
        producer.close();
    }
    
    /**
     * Donne la couleur du joueur
     * @param joueurId      Joueur dont on veut la couleur
     * @return              Couleur du joueur
     */
    @Override
    public Color getPlayerColor(Long joueurId) {
        Joueur joueur = joueurFacade.find(joueurId);
        if(joueur == null) {
            return Color.WHITE;
        }

        return new Color(255);
    }
}
