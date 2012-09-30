
import Bean.*;
import java.beans.Beans;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author T4g1
 */
public class TestBean {
    public static void main(String[] args){
        try {
            BeanDBAccessMySQL dbaMysql;
            BeanDBAccessCSV dbaCSV;
            
            dbaMysql = (BeanDBAccessMySQL)Beans.instantiate(
                    null, "Bean.BeanDBAccessMySQL");
            dbaCSV = (BeanDBAccessCSV)Beans.instantiate(
                    null, "Bean.BeanDBAccessCSV");
            
            // Connexion a la BDD
            dbaMysql.init();
            dbaCSV.init();
            
            System.out.println("Chambres: " + dbaMysql.count("chambres"));
            System.out.println("Agents: " + dbaCSV.count("F_AGENTS"));
            
            // Deconnexion
            dbaMysql.disconnect();
            dbaCSV.disconnect();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TestBean.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
}
