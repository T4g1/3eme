/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Piece;
import java.awt.Color;
import java.awt.Point;
import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

/**
 * Interface remote de GameSession
 * @author T4g1
 */
@Remote
public interface GameSessionRemote {
    Collection<Piece> getListPieces(Long id);
    Piece pieceOnEchiquierAt(Long echiquierId, int x, int y);
    List<Point> whereCanItGo(Long echiquierId, Long pieceId);
    int moveTo(Long echiquierId, Long pieceId, int x, int y);
    Long whosTurnIsIt(Long id);
    void playerLeave(Long joueurId, Long echiquierId);
    Color getPlayerColor(Long joueurId);
}
