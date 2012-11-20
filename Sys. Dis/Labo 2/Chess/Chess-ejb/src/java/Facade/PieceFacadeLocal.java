/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Piece;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author T4g1
 */
@Local
public interface PieceFacadeLocal {

    void create(Piece piece);

    void edit(Piece piece);

    void remove(Piece piece);

    Piece find(Object id);

    List<Piece> findAll();

    List<Piece> findRange(int[] range);

    int count();
    
}
