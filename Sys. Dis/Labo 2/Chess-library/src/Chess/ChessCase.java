package Chess;

import Entity.Piece;

/**
 *
 * @author T4g1
 */
public class ChessCase {
    public Piece piece = null;
    
    public ChessCase() {
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
