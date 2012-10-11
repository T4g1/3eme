/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.BeanDBAccessCSV;
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
 *
 * @author T4g1
 */
public class loginServlet extends HttpServlet {
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
            throws ServletException, IOException 
    {
        //préparation du header pour la réponse
        response.setContentType("text/html;charset=UTF-8");
        
        //création du fichier de réponse
        out = response.getWriter();
        Vues.begin(out);
        
        // Vérifie l'action
        String action = request.getParameter("action");
        if( action.equals("login"))
        {
            //redirection vers la page contenant les 3 options
            if(onLogin(request))
                response.sendRedirect("http://localhost:8090/Web_Applic_Reservations/OptionPage.html");
        }
        else if( action.equals("adduser"))
        {
            onAdduser(request);
        }
        
        //finition du fichier + fermeture
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
            BeanDBAccessCSV dba = (BeanDBAccessCSV)Beans.instantiate(
                    null, "Bean.BeanDBAccessCSV");
            
            if(dba.init()) {
                // Verifie si l'user existe deja
                if(dba.count("F_AGENTS", "username='" + username+"'") != 0)
                {
                    Vues.showAddUserFailed(out);
                    return false;
                }
                
                // Ajout dans la base de données
                else {
                    String query = "INSERT INTO F_AGENTS";
                    query += " ('username', 'password')";
                    query += " VALUES ";
                    query += "('" + username + "', '" + password + "')";
                    
                    dba.executeQuery(query);
                    
                    // TODO
                    // session.logged = true
                
                    return true;
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
            // TODO
            // session.logged = true
            
            
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
    private boolean verifyLogin(String username, String password)
    {
       try {
            BeanDBAccessCSV dba = (BeanDBAccessCSV)Beans.instantiate(
                    null, "Bean.BeanDBAccessCSV");
            
            if(dba.init()) {
                // Sélectionne les informations de l'utilisateur
                ResultSet result = dba.selectAll("F_AGENTS", "username='" + username+"'");
                result.next();

                Vues.addMessage(out, "resultat: " + result.getString("username")+" | "+ result.getString("password"));
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
