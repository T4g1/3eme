/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.File;
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
    private String url;
    
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
        // Création de l'URL
        String sep = System.getProperty("file.separator");
        
        url = "jdbc:relique:csv:";
        url += new File(System.getProperty("user.dir")).getParent();
        url += sep + "webapps" + sep + "BDD";
        
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
            
            setConnection(DriverManager.getConnection(url, properties));
        } catch (SQLException ex) {
            Logger.getLogger(BeanDBAccessCSV.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
