package Session;

import Entity.Cavalier;
import Entity.Fou;
import Entity.Piece;
import Entity.Pion;
import Entity.Reine;
import Entity.Roi;
import Entity.Tour;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Fonction gérant la partie en cours
 * @author T4g1
 */
@Stateless
public class GameSession implements GameSessionRemote {
    /**
     * Donne la liste des piéces en jeu au joueur
     * @return  Liste de piéces et leurs coordonnées
     */
    @Override
    public List<Piece> getListPieces() {
        List<Piece> l_pieces = new ArrayList<Piece>();
        
        l_pieces.add(new Tour    (0, 0, Color.BLACK));
        l_pieces.add(new Cavalier(1, 0, Color.BLACK));
        l_pieces.add(new Fou     (2, 0, Color.BLACK));
        l_pieces.add(new Reine   (3, 0, Color.BLACK));
        l_pieces.add(new Roi     (4, 0, Color.BLACK));
        l_pieces.add(new Fou     (5, 0, Color.BLACK));
        l_pieces.add(new Cavalier(6, 0, Color.BLACK));
        l_pieces.add(new Tour    (7, 0, Color.BLACK));
        l_pieces.add(new Pion    (0, 1, Color.BLACK));
        l_pieces.add(new Pion    (1, 1, Color.BLACK));
        l_pieces.add(new Pion    (2, 1, Color.BLACK));
        l_pieces.add(new Pion    (3, 1, Color.BLACK));
        l_pieces.add(new Pion    (4, 1, Color.BLACK));
        l_pieces.add(new Pion    (5, 1, Color.BLACK));
        l_pieces.add(new Pion    (6, 1, Color.BLACK));
        l_pieces.add(new Pion    (7, 1, Color.BLACK));
        
        l_pieces.add(new Tour    (0, 7, Color.WHITE));
        l_pieces.add(new Cavalier(1, 7, Color.WHITE));
        l_pieces.add(new Fou     (2, 7, Color.WHITE));
        l_pieces.add(new Reine   (3, 7, Color.WHITE));
        l_pieces.add(new Roi     (4, 7, Color.WHITE));
        l_pieces.add(new Fou     (5, 7, Color.WHITE));
        l_pieces.add(new Cavalier(6, 7, Color.WHITE));
        l_pieces.add(new Tour    (7, 7, Color.WHITE));
        l_pieces.add(new Pion    (0, 6, Color.WHITE));
        l_pieces.add(new Pion    (1, 6, Color.WHITE));
        l_pieces.add(new Pion    (2, 6, Color.WHITE));
        l_pieces.add(new Pion    (3, 6, Color.WHITE));
        l_pieces.add(new Pion    (4, 6, Color.WHITE));
        l_pieces.add(new Pion    (5, 6, Color.WHITE));
        l_pieces.add(new Pion    (6, 6, Color.WHITE));
        l_pieces.add(new Pion    (7, 6, Color.WHITE));
        
        return l_pieces;
    }

    /**
     * Donne tous les déplacements possible de la piéce
     * @param piece     Pieces dont on veut connaître les déplacements
     * @return          Liste des déplacements possible
     */
    @Override
    public List<Point> authorizedMove(Piece piece) {
        List<Point> l_position = new ArrayList<Point>();
        
        return l_position;
    }
}
