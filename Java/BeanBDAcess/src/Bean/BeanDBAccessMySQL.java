/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;

/**
 * Gére les connexions a une base de données MySQL
 * 
 * @author T4g1
 */
public class BeanDBAccessMySQL implements Serializable {
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String DATABASE_NAME = "BD_HOTELS";
    
    /**
     * Constructeur par défaut
     */
    public BeanDBAccessMySQL() {
    }
    
    /**
     * Initialise la connection MySQL
     */
    public boolean init() {
        return false;
    }

    //<editor-fold defaultstate="collapsed" desc="Accesseurs">
    /**
     * Donne le DRIVER utilisé
     * 
     * @return  Driver utilisé
     */
    public String getDRIVER() {
        return DRIVER;
    }

    /**
     * Donne le USERNAME utilisé
     * 
     * @return  Username utilisé
     */
    public String getUSERNAME() {
        return USERNAME;
    }

    /**
     * Donne le PASSWORD utilisé
     * 
     * @return  PASSWORD utilisé
     */
    public String getPASSWORD() {
        return PASSWORD;
    }

    /**
     * Donne le HOST utilisé
     * 
     * @return  HOST utilisé
     */
    public String getHOST() {
        return HOST;
    }

    /**
     * Donne le PORT utilisé
     * 
     * @return  PORT utilisé
     */
    public String getPORT() {
        return PORT;
    }

    /**
     * Donne le nom de BDD utilisé
     * 
     * @return  Nom de BDD utilisé
     */
    public String getDATABASE_NAME() {
        return DATABASE_NAME;
    }
    // </editor-fold>
}
