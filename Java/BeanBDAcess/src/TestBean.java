
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
            BeanDBAccessMySQL beanDBAccess;
            beanDBAccess = (BeanDBAccessMySQL)Beans.instantiate(
                    null, "Bean.BeanDBAccessMySQL");
            
            // Connexion a la BDD
            beanDBAccess.init();
            
            System.out.println(beanDBAccess.count("chambres"));
            
            // Deconnexion
            beanDBAccess.disconnect();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TestBean.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
}
