package Servlet;

import Bean.BeanDBAccessMySQL;
import Session.UserInfo;
import Vues.Vues;
import java.beans.Beans;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
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
        
        // Récupére les informations utilisateur
        userinfo = UserInfo.getUserInfo(request);
        
        out = response.getWriter();
        Vues.begin(out);
        
        // Vérifie l'action
        String action = request.getParameter("action");
        if( (action.equals("login") && onLogin(request)) ||
            (action.equals("adduser") && onAdduser(request)))
        {
            userinfo.setPage("userpanel");
            Vues.redirect(request, response, "index");
        }
        
        Vues.end(out);
        out.close();
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
        
        try {
            BeanDBAccessMySQL dba = (BeanDBAccessMySQL)Beans.instantiate(
                    null, "Bean.BeanDBAccessMySQL");
            
            if(dba.init()) {
                // Verifie si l'user existe deja
                if(dba.count("voyageurs", "username='" + username + "'") != 0)
                {
                    Vues.showAddUserFailed(out);
                }
                
                // Ajout dans la base de données
                else {
                    String query = "INSERT INTO voyageurs";
                    query += " (id, username, password)";
                    query += " VALUES ";
                    query += "(NULL, '" + username + "', '" + password + "');";
                    
                    if(dba.executeUpdate(query))
                    {
                        // Log l'utilisateur
                        userinfo.setLogged(true);
                        
                        return true;
                    }
                    
                    Vues.addMessage(out, "Echec de la commande: " + query);
                }
            }
        }
        catch (IOException ex) {
            Vues.addMessage(out, "Erreur de connexion: "+ ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Vues.addMessage(out, "Erreur de connexion: "+ ex.getMessage());
        }
        
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
        
        // Login réussit
        if(verifyLogin(username, password))
        {
            // Log l'utilisateur
            userinfo.setLogged(true);
            
            return true;
        }
        // Login échoué
        else
        {
            Vues.showLoginFailed(out);
        }
        
        return false;
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
            BeanDBAccessMySQL dba = (BeanDBAccessMySQL)Beans.instantiate(
                    null, "Bean.BeanDBAccessMySQL");
            
            if(dba.init()) {
                // Sélectionne les informations de l'utilisateur
                ResultSet result = dba.selectAll("voyageurs", "username='" + username+"'");
                result.next();
                
                return password.equals(result.getString("password"));
            }
            else
            {
                Vues.addMessage(out, "Connexion impossible");
            }
        }
        catch (SQLException ex) {
            Vues.addMessage(out, "Erreur de connexion: "+ ex.getMessage());
        } 
        catch (IOException ex) {
            Vues.addMessage(out, "Erreur de connexion: "+ ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Vues.addMessage(out, "Erreur de connexion: "+ ex.getMessage());
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
