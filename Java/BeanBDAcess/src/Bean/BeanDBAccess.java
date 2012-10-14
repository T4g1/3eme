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
     * Exécute la requête donnée (INSERT, UPDATE)
     * 
     * @param query         Requête que l'on souhaite exectuer
     * 
     * @return              true si requête ok, false sinon
     */
    public boolean executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query) == 1;
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccess.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Donne le nombre d'enregistrements dans la table donnée
     * 
     * @param table     Table dont on souhaite connaître
     *                  le nombre d'enregistrements
     * @param condition     Contenu de la clause WHERE
     * 
     * @return          Nombre d'enregistrements, -1 si erreur
     */
    public int count(String table, String condition) {
        try {
            String query = "SELECT COUNT(*) AS countEntry FROM " + table;
            if(!condition.equals(""))
            {
                query += " WHERE " + condition;
            }
            
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
    
    public int count(String table) {
        return count(table, "");
    }
    
    /**
     * Donne les enregistrements de la table donnée
     * 
     * @param table         Table dont on souhaite récuperer les enregistrements
     * @param condition     Contenu de la clause WHERE
     * 
     * @return              ResultSet contenant les enregistrements
     */
    public ResultSet selectAll(String table, String condition) {
        String query = "SELECT * FROM " + table;
        if(!condition.equals(""))
        {
            query += " WHERE " + condition;
        }
        
        return executeQuery(query);
    }
    
    public ResultSet selectAll(String table) {
        return selectAll(table, "");
    }
}
