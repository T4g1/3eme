/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testentity;

import Entity.NewEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author T4g1
 */
public class TestEntity {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NewEntity e = new NewEntity();
        
        persist(e);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestEntityPU");
        EntityManager em = emf.createEntityManager();
        //em.find(NewEntity, );
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestEntityPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
