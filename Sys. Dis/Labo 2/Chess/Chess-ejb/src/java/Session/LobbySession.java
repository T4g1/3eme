package Session;

import Entity.Echiquier;
import Entity.Joueur;
import Entity.Piece;
import Facade.EchiquierFacadeLocal;
import Facade.JoueurFacadeLocal;
import Facade.PieceFacadeLocal;
import JMS.JMSProducer;
import java.awt.Color;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Permet au lobby du jeu de récuperer des parties en cours, d'en créer ou
 * de les rejoindre
 * @author T4g1
 */
@Stateless
public class LobbySession implements LobbySessionRemote {
    @EJB
    private PieceFacadeLocal pieceFacade;
    @EJB
    private JoueurFacadeLocal joueurFacade;
    @EJB
    private EchiquierFacadeLocal echiquierFacade;
    /**
     * Donne la liste des parties en cours
     * @return      Liste d'echiquier
     */
    @Override
    public List<Echiquier> getListing() {
        return echiquierFacade.findAll();
    }
    
    /**
     * Crée un nouveau joueur
     * @return  Nouveau joueur
     */
    @Override
    public Joueur createJoueur() {
        Joueur joueur = new Joueur();
        
        joueurFacade.create(joueur);
        
        return joueur;
    }

    /**
     * Crée une nouvelle partie
     * @param nom   Nom de la partie
     * @return      Id de la partie, -1 si erreur
     */
    @Override
    public long createEchiquier(String nom, Joueur joueur1) {
        // Celui qui crée la partie prend la couleur blanche
        joueur1.setColor(Color.WHITE);
        joueurFacade.edit(joueur1);
        
        Echiquier echiquier = new Echiquier();
        echiquier.init(nom);
        echiquier.setJoueur1(joueur1);
        
        // Persist les piéces
        for(Piece piece: echiquier.getListPiece()) {
            pieceFacade.create(piece);
        }
        
        // Persist l'echiquier
        echiquierFacade.create(echiquier);
        
        return echiquier.getId();
    }

    /**
     * Rejoins la partie dont l'id est donné
     * @param id    Id de la partie
     * @return      1 en cas de succes, -1 si erreur, 0 si partie pleine
     */
    @Override
    public int joinEchiquier(long id, Joueur joueur2) {
        Echiquier echiquier = echiquierFacade.find(id);
        if(echiquier == null) {
            return -1;
        }
        
        if(echiquier.getPlayerCount() >= 2) {
            return 0;
        }
        
        joueur2.setColor(Color.BLACK);
        joueurFacade.edit(joueur2);
        
        echiquier.setJoueur2(joueur2);
        echiquierFacade.edit(echiquier);
        
        // Debut de la partie
        JMSProducer producer = new JMSProducer();
        producer.sendMessage(echiquier.getJoueur1().getId(), "GAME_START");
        producer.sendMessage(echiquier.getJoueur2().getId(), "GAME_START");
        producer.close();
        
        return 1;
    }
}
