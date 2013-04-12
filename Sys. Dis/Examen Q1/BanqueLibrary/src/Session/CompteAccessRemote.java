package Session;

import Entity.Compte;
import java.util.List;
import javax.ejb.Remote;

/**
 * Donne accès aux comptes utilisateur
 */
@Remote
public interface CompteAccessRemote {
    public List<Compte> getCompteList(String pseudo, String password);
    public Long getClient(String pseudo, String password);
    public Long createClient(String pseudo, String password);
    public Long createCompte(String pseudo, String password);
    public boolean addSolde(String pseudo, String password, Long compteId, int value);
    public boolean removeSolde(String pseudo, String password, Long compteId, int value);
}
