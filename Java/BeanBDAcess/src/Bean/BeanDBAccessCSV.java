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
    public boolean init(String purl) {
        String fp = System.getProperty("file.separator");
        //url t4g1
        //String url = "jdbc:relique:csv:F:"+fp+"3eme"+fp+"Java"+fp+"BDD";
        //chemin delskev
        //url = "jdbc:relique:csv:D:"+fp+"Ecole"+fp+"Kevin"+fp+"Réseaux_et_Technologies_Internet"+fp+"gitApplic"+fp+"3eme"+fp+"Java"+fp+"BDD"+fp;
        //pour mettre la bd au même niveau que le .war sur le serveur 
        
        //url = "jdbc:relique:csv:";
        //url += new File(System.getProperty("user.dir") ).getParent()+ fp+ "webapps"+fp+"BDD"+fp;
        //url = "jdbc:relique:csv:http://localhost:8090/BDD/F_AGENTS.csv";
        url = purl;
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
