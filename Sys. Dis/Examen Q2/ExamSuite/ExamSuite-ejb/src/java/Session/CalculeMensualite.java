/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Simulation;
import Facade.SimulationFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author T4g1
 */
@Stateless
public class CalculeMensualite implements CalculeMensualiteRemote {
    @EJB
    private SimulationFacadeLocal simulationFacade;
    
    @Override
    public Simulation getMensualite(int captial, int duree, int ta) {
        Simulation simulation = new Simulation();
        simulation.setCapital(captial);
        simulation.setDuree(duree);
        simulation.setTa(ta);
        simulation.run();
        simulationFacade.create(simulation);
        
        return simulation;
    }
    
}
