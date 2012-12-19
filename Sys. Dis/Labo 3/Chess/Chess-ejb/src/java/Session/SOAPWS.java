package Session;

import Entity.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Web Service
 */
@WebService(serviceName = "SOAPWS")
@Stateless()
@HandlerChain(file = "SOAPWS_handler.xml")
public class SOAPWS {

    @PersistenceUnit
    EntityManagerFactory emf;

    /**
     * Récupérer joueur
     */
    @WebMethod(operationName = "GetPlayer")
    public List<Joueur> GetPlayer(
            @WebParam(name = "username", header=true) String username,
            @WebParam(name = "password", header=true) String password)
    {
        List<Joueur> player = 
                emf.createEntityManager().createNamedQuery("all").getResultList();
        
        return player;
    }

    /**
     * Ajouter joueur
     */
    @WebMethod(operationName = "AddPlayer")
    public boolean AddPlayer(
            @WebParam(name = "username", header=true) String username,
            @WebParam(name = "password", header=true) String password,
            @WebParam(name = "pseudo") String pseudo)
    {
        Joueur player = new Joueur();
        player.setPseudo(pseudo);
        
        emf.createEntityManager().persist(player);
        
        return true;
    }

    /**
     * Retirer joueur
     */
    @WebMethod(operationName = "RemovePlayer")
    public boolean RemovePlayer(
            @WebParam(name = "username", header=true) String username, 
            @WebParam(name = "password", header=true) String password, 
            @WebParam(name = "id") long id)
    {
        EntityManager entity = emf.createEntityManager();
        
        Joueur player = entity.find(Joueur.class, id);
        
        // Si on a récupéré le joueur
        if(player != null) {
            entity.remove(player);
            
            return true;
        }
        
        return false;
    }

    /**
     * Modifier joueur
     */
    @WebMethod(operationName = "AlterUser")
    public boolean AlterUser(
            @WebParam(name = "username", header=true) String username, 
            @WebParam(name = "password", header=true) String password, 
            @WebParam(name = "id") long id, @WebParam(name = "pseudo") String pseudo)
    {
        EntityManager entity = emf.createEntityManager();
        
        Joueur player = entity.find(Joueur.class, id);
        
        // Si on a récupéré le joueur
        if(player != null) {
            player.setPseudo(pseudo);
            
            entity.merge(player);
            
            return true;
        }
        
        return false;
    }

    /**
     * Récuperer les parties gagnées
     */
    @WebMethod(operationName = "getHighScore")
    public List<HighScore> getHighScore(
            @WebParam(name = "username", header=true) String username, 
            @WebParam(name = "password", header=true) String password) 
    {
        EntityManager em = emf.createEntityManager();
        
        List<HighScore> players = em.createQuery(
                "select new Session.HighScore(j.pseudo, count(e.id)) "
                + "from Joueur j, Echiquier e "
                + "where j.id = e.gagnant.id group by j.id "
                + "order by count(e.id) desc").getResultList();
        
        return players;
    }

    /**
     * Récupérer les parties jouées
     */
    @WebMethod(operationName = "GetPlayByPlayer")
    public List<HighScore> GetPlayByPlayer(
            @WebParam(name = "username", header=true) String username, 
            @WebParam(name = "password", header=true) String password) 
    {
        EntityManager em = emf.createEntityManager();
        
        List<HighScore> players = em.createQuery(
                "select new Session.HighScore(j.pseudo, count(e.id)) "
                + "from Joueur j, Echiquier e "
                + "where j.id = e.JBlanc.id OR j.id = e.JNoir.id "
                + "group by j.id order by count(e.id) desc").getResultList();
        
        return players;
    }

    /**
     * Récupére les parties perdues
     */
    @WebMethod(operationName = "GetLooseByPlayer")
    public List<HighScore> GetLooseByPlayer(
            @WebParam(name = "username", header=true) String username, 
            @WebParam(name = "password", header=true) String password) 
    {
        EntityManager em = emf.createEntityManager();
        
        List<HighScore> players = em.createQuery(
                "select new Session.HighScore(j.pseudo, count(e.id)) "
                + "from Joueur j, Echiquier e "
                + "where (j.id = e.JBlanc.id OR j.id = e.JNoir.id) AND j.id != e.gagnant.id "
                + "group by j.id order by count(e.id) desc").getResultList();
        
        return players;
    }
}


