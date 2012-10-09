/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Bean.BeanDBAccessCSV;
import Vues.Vues;
import java.beans.Beans;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Vues.addHtmlHeader(out);
        
        String action = request.getParameter("action");
        // Vérifie l'action
        if( action.equals("login"))
        {
            onLogin(request);
        }
        else{
            Vues.addLoginError(out, request);
        }
        //finition du fichier + fermeture
        Vues.addHtmlBottom(out);
        out.close();
    }
    
    /**
     * Appellée lorsque l'on veut se logger (action login)
     * 
     * @param request       Requête recue
     */
    private void onLogin(HttpServletRequest request) {
        // Récupére les informations de connexion
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Login réussit
        if(verifyLogin(username, password))
        {
            Vues.addLoginSuccess(out, username);
        }
        // Login échoué
        else
        {
            Vues.showLoginFailed(out);
            Vues.addmessage(out, "bad username/password");
        }
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
            
            BeanDBAccessCSV dba = 
                    (BeanDBAccessCSV)Beans.instantiate(null, "Bean.BeanDBAccessCSV");
            
            
            if( dba.init("jdbc:relique:csv:D:\\Serveur\\apache-tomcat-7.0.30\\webapps\\BDD") ){
                // Sélectionne les informations de l'utilisateur
                ResultSet result = dba.selectAll("F_AGENTS", "username='" + username+"'");
                result.next();

                Vues.addmessage(out, "resultat: "+result.getString("username")+" | "+ result.getString("password"));
                return password.equals(result.getString("password"));
            }
            
            
        }
        catch (SQLException ex) {
            Vues.addmessage(out, "Erreur de connexion: "+ ex.getMessage());
        } 
        catch (IOException ex) {
            Vues.addmessage(out, "Erreur de connexion: "+ ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Vues.addmessage(out, "Erreur de connexion: "+ ex.getMessage());
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
