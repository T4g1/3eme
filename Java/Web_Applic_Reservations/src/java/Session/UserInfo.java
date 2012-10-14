/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Vues.Vues;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Informations de l'utilisateur
 * 
 * @author T4g1
 */
public class UserInfo {
    public static final String USER_INFO_KEY = "USER_INFO_KEY";
    private boolean logged;
    private String page;
    
    /**
     * Initialisation du bean
     */
    @PostConstruct
    private void initialize() {
        logged = false;
        page = "acceuil";
    }
    
    /**
     * Récupére les informations utilisateur
     * 
     * @param request       Requete recue par le servlet
     * 
     * @return              Les informations utilisateur
     */
    public static UserInfo getUserInfo(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(true);
        UserInfo userInfo = (UserInfo)httpSession.getAttribute(UserInfo.USER_INFO_KEY);
        if (userInfo == null) {
            userInfo = new UserInfo();
            
            httpSession.setAttribute(UserInfo.USER_INFO_KEY, userInfo);
        }
        
        return userInfo;
    }
    
    /**
     * Annule le caddie actuel du client
     */
    public void cancelCaddie() {
        // TODO
    }
    
    //<editor-fold defaultstate="collapsed" desc="Accesseurs">
    
    /**
     * Indique si l'utilisateur est loggué
     * 
     * @return      true s'il est loggué, false sinon
     */
    public boolean isLogged() {
        return logged;
    }
    
    /**
     * Donne la page actuelle
     * 
     * @return      Page actuelle
     */
    public String getPage() {
        return page;
    }
    
    /**
     * Donne le titre de la page actuelle
     * 
     * @return      Titre de la page
     */
    public String getPageTitle() {
        return Vues.getPageTitle(page);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mutateurs">
    
    /**
     * Modifie la valeur de logged
     * 
     * @param value         Nouvelle valeur
     */
    public void setLogged(boolean value) {
        logged = value;
    }
    
    /**
     * Modifie la page actuelle
     * 
     * @param value         Nouvelle page
     */
    public void setPage(String value) {
        page = value;
    }
    
    //</editor-fold>
}
