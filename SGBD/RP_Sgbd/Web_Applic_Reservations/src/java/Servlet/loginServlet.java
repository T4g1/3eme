package Servlet;

import Bean.BeanDBAccessORA;
import Session.UserInfo;
import Vues.Vues;
import java.beans.Beans;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet gérant le login du client
 * 
 * @author T4g1
 */
public class loginServlet extends HttpServlet {
    private UserInfo userinfo;
    private PrintWriter out;
    private String message;
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //préparation du header pour la réponse
        response.setContentType("text/html;charset=UTF-8");
        Vues.redirect(request, response, "index");
    }
    
    /**
     * Appellée lorsque l'on veut ajouter un utilisateur (action adduser
     * 
     * @param request       Requête recue
     * 
     * @return      true si l'ajout s'est fait, false sinon
     */
    private boolean onAdduser(HttpServletRequest request) {
        // Récupére les informations de connexion
        String username = request.getParameter("username");
        String password = request.getParameter("password");
 /*      
        try {
            BeanDBAccessORA dba = (BeanDBAccessORA)Beans.instantiate(
                    null, "Bean.BeanDBAccessMySQL");
            
            if(dba.init()) {
                // Verifie si l'user existe deja
                if(dba.count("voyageurs", "username='" + username + "'") != 0)
                {
                    message = "Utilisateur déjà présent dans la BDD";
                }
                
                // Ajout dans la base de données
                else {
                    String query = "INSERT INTO voyageurs";
                    query += " (id, username, password)";
                    query += " VALUES ";
                    query += "(NULL, '" + username + "', '" + password + "');";
                    
                    if(!dba.executeUpdate(query))
                    {
                        message = "AddUser: Execution de la requete SQL échoué";
                        return false;
                    }
                    
                    // Récupére l'Id du voyageur
                    ResultSet result = dba.selectAll("voyageurs", 
                            "username='" + username + "'");
                    
                    if(!result.next())
                    {
                        message = "Impossible de récuperer l'id du voyageur";
                        return false;
                    }
                    
                    int id = result.getInt("id");
                    
                    // Log l'utilisateur
                    userinfo.setId(id);
                    userinfo.setLogged(true);

                    return true;
                }
            }
            else
            {
                message = "BDD impossible à initialiser";
            }
            
        } catch (SQLException ex) {
            message = "AddUser: SQLException";
        } catch (IOException ex) {
            message = "AddUser: IOException";
        } catch (ClassNotFoundException ex) {
            message = "AddUser: ClassNotFoundException";
        }
        */
        return false;
    }
    
    /**
     * Appellée lorsque l'on veut se logger (action login)
     * 
     * @param request       Requête recue
     * 
     * @return      true si le login s'est fait, false sinon
     */
    private boolean onLogin(HttpServletRequest request) {
        // Récupére les informations de connexion
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        return verifyLogin(username, password);
    }
    
    /**
     * Vérifie le couple user/pass
     * 
     * @param username      Nom d'utilisateur
     * @param password      Mot de passe
     * 
     * @return              true si l'utilisateur a donné les bonnes
     *                      informations, false sinon
     */
    private boolean verifyLogin(String username, String password) {
       try {
            BeanDBAccessORA dba = (BeanDBAccessORA)Beans.instantiate(
                    null, "Bean.BeanDBAccessORA");
            
            if(dba.init()) {
                // Sélectionne les informations de l'utilisateur
                ResultSet result = dba.selectAll("voyageurs", "username='" + username+"'");
                if(result.next())
                {
                    if(password.equals(result.getString("password")))
                    {
                        userinfo.setId(result.getInt("id"));
                        userinfo.setLogged(true);
                        
                        return true;
                    }
                }
                else
                {
                    message = "Utilisateur inexistant";
                }
            }
            else
            {
                message = "BDD impossible à initialiser";
            }
        }
        catch (SQLException ex) {
            message = "verifyLogin: SQLException";
        } 
        catch (IOException ex) {
            message = "verifyLogin: IOException";
        } catch (ClassNotFoundException ex) {
            message = "verifyLogin: ClassNotFoundException";
        }
       
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
