/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Fou;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author T4g1
 */
@Local
public interface FouFacadeLocal {

    void create(Fou fou);

    void edit(Fou fou);

    void remove(Fou fou);

    Fou find(Object id);

    List<Fou> findAll();

    List<Fou> findRange(int[] range);

    int count();
    
}
