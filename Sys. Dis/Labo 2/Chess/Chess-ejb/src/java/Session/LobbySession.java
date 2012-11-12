package Session;

import Entity.Echiquier;
import javax.ejb.Stateless;

/**
 * Permet au lobby du jeu de récuperer des parties en cours, d'en créer ou
 * de les rejoindre
 * @author T4g1
 */
@Stateless
public class LobbySession implements LobbySessionRemote {
    private Echiquier[] l_echiquier;
    /**
     * Donne la liste des parties en cours
     * @return      Liste d'echiquier
     */
    @Override
    public Echiquier[] getListing() {
        int nb_parties = (int)(Math.random() * (100 - 10)) + 10;
        l_echiquier = new Echiquier[nb_parties];
        
        for(int i=0; i<nb_parties; i++) {
            l_echiquier[i] = new Echiquier();
            l_echiquier[i].setNom("Partie sans nom " + (i+1) + "/" + nb_parties);
        }
        
        return l_echiquier;
    }

    /**
     * Crée une nouvelle partie
     * @param nom   Nom de la partie
     * @return      Id de la partie, -1 si erreur
     */
    @Override
    public long createEchiquier(String nom) {
        Echiquier echiquier = new Echiquier();
        echiquier.setNom(nom);
        
        // TODO
        
        return -1;
    }

    /**
     * Rejoins la partie dont l'id est donné
     * @param id    Id de la partie
     * @return      1 en cas de succes, -1 si erreur, 0 si partie pleine
     */
    @Override
    public int joinEchiquier(long id) {
        // TODO
        
        return -1;
    }
}
