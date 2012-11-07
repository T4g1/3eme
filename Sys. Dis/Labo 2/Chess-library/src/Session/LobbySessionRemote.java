package Session;

import Entity.Echiquier;
import javax.ejb.Remote;

/**
 * Interface remote de LobbySession
 * @author T4g1
 */
@Remote
public interface LobbySessionRemote {
    Echiquier[] getListing();
    long createEchiquier(String nom);
    int joinEchiquier(long id);
}
