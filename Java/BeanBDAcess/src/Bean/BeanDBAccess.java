/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T4g1
 */
public class BeanDBAccess {
    protected Connection connection = null;
    protected Statement statement = null;
    
    /**
     * Constructeur par défaut
     */
    public BeanDBAccess() {
    }
    
    /**
     * Initialise la connection MySQL
     */
    public boolean init() {
        return false;
    }
    
    /**
     * Construit l'URL vers la BDD voulue
     * 
     * @return      URL de la BDD
     */
    protected String buildURL(String host, String port, String dbName) {
        return "jdbc:mysql://" + host + ":" + port + "/" + dbName;
    }
    
    /**
     * Initialise le driver donné
     * 
     * @param driver    Driver que l'on souhaite charger
     * 
     * @return          true si le driver est chargé, false sinon
     */
    protected boolean initDriver(String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BeanDBAccess.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }

    /**
     * Donne la connection a la BDD
     * 
     * @param url           URL de la BDD
     * @param username      Nom d'utilisateur
     * @param password      Mot de passe
     * 
     * @return              Connection a la BDD
     */
    public boolean connect(
            String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccess.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Termine la connection en cours
     */
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccess.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
}
