package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import resource.Echiquier;

/**
 * Facade Echiquier
 */
@Stateless
@Path("resource.echiquier")
public class EchiquierFacadeREST extends AbstractFacade<Echiquier> {
    @PersistenceContext(unitName = "SysDist4PU")
    private EntityManager em;
    public static final String URL = "http://localhost:8080/SysDist4/webresources/resource.joueur/";

    public EchiquierFacadeREST() {
        super(Echiquier.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Echiquier entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Echiquier entity) {
        super.edit(entity);
    }
    
    

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("partie/{id}")
    @Produces({"application/xml", "application/json"})
    public Echiquier find(@PathParam("id") Long id) {
        Echiquier MyEchequier = super.find(id);
        return MyEchequier;
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Echiquier> findAll() {
        List<Echiquier> listechiquier = em.createQuery("select e from Echiquier e").getResultList();
        return listechiquier;
    }
    
    @GET
    @Path("partie")
    @Produces({"application/xml", "application/json"})
    public List<Echiquier> findGames(@QueryParam("terminee") String terminee) {
        if("oui".equals(terminee)){
            return em.createQuery("select e from Echiquier e where e.gameover = 1").getResultList();
        }
        else if("non".equals(terminee)){
            return em.createQuery("select e from Echiquier e where e.gameover = 0").getResultList();
        }
        else{
            return super.findAll();
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Echiquier> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
