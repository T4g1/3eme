/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Reine;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author T4g1
 */
@Local
public interface ReineFacadeLocal {

    void create(Reine reine);

    void edit(Reine reine);

    void remove(Reine reine);

    Reine find(Object id);

    List<Reine> findAll();

    List<Reine> findRange(int[] range);

    int count();
    
}
