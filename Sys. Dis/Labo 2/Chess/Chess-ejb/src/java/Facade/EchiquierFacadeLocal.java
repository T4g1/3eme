/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Echiquier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author T4g1
 */
@Local
public interface EchiquierFacadeLocal {

    void create(Echiquier echiquier);

    void edit(Echiquier echiquier);

    void remove(Echiquier echiquier);

    Echiquier find(Object id);

    List<Echiquier> findAll();

    List<Echiquier> findRange(int[] range);

    int count();
    
}
