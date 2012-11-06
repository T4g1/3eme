/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gére les connexions a une base de données MySQL
 * 
 * @author T4g1
 */
public class BeanDBAccessMySQL extends BeanDBAccess {
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String DBNAME = "bd_hotels";
    
    /**
     * Constructeur par défaut
     */
    public BeanDBAccessMySQL() {
    }
    
    /**
     * Initialise la connection MySQL
     */
    @Override
    public boolean init() {
        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;
        
        // Initialise le driver
        if(!initDriver(DRIVER)) {
            return false;
        }
        
        // Connection a la BDD
        if(!connect(url, USERNAME, PASSWORD)) {
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
            setConnection(DriverManager.getConnection(url, username, password));
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccess.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Exécute la requête donnée
     * 
     * @param query         Requête que l'on souhaite exectuer
     * @param args          Arguments de la requête
     * 
     * @return              Résultat de la requête
     */
    public ResultSet executeQuery(String query, ArrayList args) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            
            // Applique les arguments à la query
            for(int i=0; i<args.size(); i++) {
                String arg = (String)args.get(i);
                statement.setString(i+1, arg);
            }
            
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccess.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public ResultSet executeQuery(String query) {
        ArrayList args = new ArrayList();
        
        return executeQuery(query, args);
    }
}
