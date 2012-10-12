/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import javax.annotation.PostConstruct;

/**
 * Informations de l'utilisateur
 * 
 * @author T4g1
 */
public class UserInfo {
    private boolean logged;
    
    /**
     * Initialisation du bean
     */
    @PostConstruct
    private void initialize() {
        logged = false;
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
    
    //</editor-fold>
}
