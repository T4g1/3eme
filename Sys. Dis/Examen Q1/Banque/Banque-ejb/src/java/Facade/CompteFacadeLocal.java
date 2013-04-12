/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Compte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author T4g1
 */
@Local
public interface CompteFacadeLocal {

    void create(Compte compte);

    void edit(Compte compte);

    void remove(Compte compte);

    Compte find(Object id);

    List<Compte> findAll();

    List<Compte> findRange(int[] range);

    int count();
    
}
