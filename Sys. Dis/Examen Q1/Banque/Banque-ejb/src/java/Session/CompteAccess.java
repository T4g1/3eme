package Session;

import Entity.Compte;
import Entity.Client;
import Facade.ClientFacadeLocal;
import Facade.CompteFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author T4g1
 */
@Stateless
public class CompteAccess implements CompteAccessRemote {
    @EJB
    private ClientFacadeLocal clientFacade;
    @EJB
    private CompteFacadeLocal compteFacade;
    
    // Récpére un client via son pseudo
    private Long getClient(String pseudo)
    {
        List<Client> l_client = clientFacade.findAll();
        for(Client client: l_client)
        {
            if(client.sameAsPseudo(pseudo))
            {
                return client.getId();
            }
        }
        
        return -1L;
    }
    
    // Récpére un client via son pseudo et password
    @Override
    public Long getClient(String pseudo, String password)
    {
        Long id = getClient(pseudo);
        if(id > -1L && clientFacade.find(id).sameAsPassword(password)) {
            return id;
        }
        
        return -1L;
    }
    
    /**
     * Donne les soldes de compte associé à l'utilisateur
     * @param pseudo
     * @param password
     * @return 
     */
    @Override
    public List<Compte> getCompteList(String pseudo, String password) {
        List<Compte> l_compteClient = new ArrayList<Compte>();
        
        Long proprietaireId = getClient(pseudo, password);
        if(proprietaireId <= -1L) {
            return l_compteClient;
        }
        
        // Cherche les comptes du client
        List<Compte> l_compte = compteFacade.findAll();
        for(Compte compte: l_compte)
        {
            // Si c'est le propriétaire du compte
            if(compte.getProprietaire().getId() == proprietaireId)
            {
                l_compteClient.add(compte);
            }
        }
        
        return l_compteClient;
    }

    // Crée un nouveau client
    @Override
    public Long createClient(String pseudo, String password) {
        // Existe déjà ?
        Long id = getClient(pseudo);
        if(id > -1L) {
            return -1L;
        }
        
        Client client = new Client();
        client.setPseudo(pseudo);
        client.setPassword(password);
        clientFacade.create(client);
        
        Compte compte = new Compte();
        compte.setSolde(10);
        compte.setProprietaire(client);
        compteFacade.create(compte);
        
        return client.getId();
    }

    // Crée un nouveau compte
    @Override
    public Long createCompte(String pseudo, String password) {
        Long proprietaireId = getClient(pseudo, password);
        if(proprietaireId <= -1L) {
            return -1L;
        }
        
        Compte compte = new Compte();
        compte.setSolde(10);
        compte.setProprietaire(clientFacade.find(proprietaireId));
        compteFacade.create(compte);
        
        return compte.getId();
    }

    @Override
    public boolean addSolde(String pseudo, String password, Long compteId, int value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeSolde(String pseudo, String password, Long compteId, int value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
