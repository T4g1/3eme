package Session;

import Entity.Echiquier;
import Entity.Joueur;
import java.util.List;
import javax.ejb.Remote;

/**
 * Interface remote de LobbySession
 * @author T4g1
 */
@Remote
public interface LobbySessionRemote {
    List<Echiquier> getListing();
    Joueur createJoueur();
    void removePlayer(Long joueurId);
    long createEchiquier(String nom, Joueur joueur1);
    int joinEchiquier(long id, Joueur joueur2);
}
