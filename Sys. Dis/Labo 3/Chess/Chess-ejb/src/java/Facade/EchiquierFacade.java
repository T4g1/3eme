/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Echiquier;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author T4g1
 */
@Stateless
public class EchiquierFacade extends AbstractFacade<Echiquier> implements EchiquierFacadeLocal {
    @PersistenceContext(unitName = "Chess-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EchiquierFacade() {
        super(Echiquier.class);
    }
    
}
