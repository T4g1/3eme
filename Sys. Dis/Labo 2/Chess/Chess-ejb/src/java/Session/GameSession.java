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
        Piece piecePrise = echiquier.getPiece(x, y);
        if(piecePrise != null) {
            // Verifier si c'est le roi
            // TODO
            
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
        
        // Previens qu'un tour est passé
        JMSProducer producer = new JMSProducer();
        producer.sendMessage(echiquier.getJoueur1().getId(), "NEXT_TURN");
        producer.sendMessage(echiquier.getJoueur2().getId(), "NEXT_TURN");
        producer.close();
        
        return 1;
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
        Echiquier echiquier = echiquierFacade.find(echiquierId);
        if(echiquier == null) {
            return;
        }
        
        Long joueur1 = echiquier.getJoueur1().getId();
        Long joueur2 = echiquier.getJoueur2().getId();
        
        JMSProducer producer = new JMSProducer();
        
        if(joueurId == joueur1) {
            producer.sendMessage(joueur2, "YOU_WON");
        }
        else {
            producer.sendMessage(joueur1, "YOU_WON");
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

        return joueur.getColor();
    }
}
