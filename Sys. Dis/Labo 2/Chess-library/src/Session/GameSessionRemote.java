/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Piece;
import java.awt.Point;
import java.util.List;
import javax.ejb.Remote;

/**
 * Interface remote de GameSession
 * @author T4g1
 */
@Remote
public interface GameSessionRemote {
    List<Piece> getListPieces();
    List<Point> authorizedMove(Piece piece);
}
