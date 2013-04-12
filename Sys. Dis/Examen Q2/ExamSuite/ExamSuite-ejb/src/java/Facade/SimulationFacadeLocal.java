/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Simulation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author T4g1
 */
@Local
public interface SimulationFacadeLocal {

    void create(Simulation simulation);

    void edit(Simulation simulation);

    void remove(Simulation simulation);

    Simulation find(Object id);

    List<Simulation> findAll();

    List<Simulation> findRange(int[] range);

    int count();
    
}
