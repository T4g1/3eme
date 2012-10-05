/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T4g1
 */
public class BeanDBAccessCSV extends BeanDBAccess {
    private final String DRIVER = "org.relique.jdbc.csv.CsvDriver";
    
    /**
     * Constructeur par défaut
     */
    public BeanDBAccessCSV() {
    }
    
    /**
     * Initialise la connection MySQL
     */
    @Override
    public boolean init() {
        //url t4g1
        //String url = "jdbc:relique:csv:F:\\3eme\\Java\\BDD";
        //chemin delskev
        String url = "jdbc:relique:csv:D:\\Ecole\\Kevin\\Réseaux_et_Technologies_Internet\\gitApplic\\3eme\\Java\\BDD\\";
        
        // Initialise le driver
        if(!initDriver(DRIVER)) {
            return false;
        }
        
        // Connection a la BDD
        if(!connect(url)) {
            return false;
        }
        
        return true;
    }

    /**
     * Donne la connection a la BDD
     * 
     * @param url           URL de la BDD
     * 
     * @return              Connection a la BDD
     */
    public boolean connect(String url) {
        try {
            Properties properties = new Properties();
            properties.put("separator", ";");
            properties.put("supressHeader", "false");
            properties.put("fileExtension", ".csv");
            
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccessCSV.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
}
