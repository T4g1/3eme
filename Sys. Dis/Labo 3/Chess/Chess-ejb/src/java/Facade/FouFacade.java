/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Fou;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author T4g1
 */
@Stateless
public class FouFacade extends AbstractFacade<Fou> implements FouFacadeLocal {
    @PersistenceContext(unitName = "Chess-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FouFacade() {
        super(Fou.class);
    }
    
}
