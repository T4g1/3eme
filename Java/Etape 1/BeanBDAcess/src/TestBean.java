
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
            
            ResultSet resultSet;
            
            System.out.println("Contenu de la table 'chambres' (cuvette = 2):");
            resultSet = dbaMysql.selectAll("chambres", "cuvette = 2");
            while(resultSet.next()) {
                System.out.print(resultSet.getInt("numero") + ", ");
                System.out.print(resultSet.getInt("douche") + ", ");
                System.out.print(resultSet.getInt("baignoire") + ", ");
                System.out.print(resultSet.getString("cuvette") + ", ");
                System.out.print(resultSet.getInt("nb_occupants") + ", ");
                System.out.println(resultSet.getInt("prix_htva"));
            }
            
            System.out.println("Contenu de la table 'F_AGENTS' :");
            resultSet = dbaCSV.selectAll("F_AGENTS");
            while(resultSet.next()) {
                System.out.print(resultSet.getString("username") + ", ");
                System.out.print(resultSet.getInt("niveau") + ", ");
                System.out.println(resultSet.getString("password"));
            }
            ResultSet result = dbaCSV.selectAll("F_AGENTS", "username='" + "paul"+"'");
            result.next();
            System.out.println("resultat keke: "+result.getString("username"));
            // Deconnexion
            dbaMysql.disconnect();
            dbaCSV.disconnect();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(TestBean.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
}
