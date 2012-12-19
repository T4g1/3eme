/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Joueur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author T4g1
 */
@Local
public interface JoueurFacadeLocal {

    void create(Joueur joueur);

    void edit(Joueur joueur);

    void remove(Joueur joueur);

    Joueur find(Object id);

    List<Joueur> findAll();

    List<Joueur> findRange(int[] range);

    int count();
    
}
