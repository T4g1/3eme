/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Simulation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author T4g1
 */
@Stateless
public class SimulationFacade extends AbstractFacade<Simulation> implements SimulationFacadeLocal {
    @PersistenceContext(unitName = "ExamSuite-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SimulationFacade() {
        super(Simulation.class);
    }
    
}
