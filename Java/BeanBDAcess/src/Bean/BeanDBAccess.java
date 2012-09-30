/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T4g1
 */
public class BeanDBAccess implements Serializable {
    protected Connection connection = null;
    
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
    
    /**
     * Exécute la requête donnée
     * 
     * @param query         Requête que l'on souhaite exectuer
     * 
     * @return              Résultat de la requête
     */
    public ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccess.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Donne le nombre d'enregistrements dans la table donnée
     * 
     * @param table     Table dont on souhaite connaître
     *                  le nombre d'enregistrements
     * 
     * @return          Nombre d'enregistrements, -1 si erreur
     */
    public int count(String table) {
        try {
            String query = "SELECT COUNT(*) AS countEntry FROM " + table;
            
            ResultSet resultSet = executeQuery(query);
            if(resultSet != null) {
                resultSet.next();
                return resultSet.getInt("countEntry");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccess.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        
        return -1;
    }
}
