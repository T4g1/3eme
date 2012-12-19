/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Session.UserInfo;
import Vues.Vues;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author freddy
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {
    UserInfo userinfo;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //préparation du header pour la réponse
        response.setContentType("text/html;charset=UTF-8");
        // Récupére les informations utilisateur
        userinfo = UserInfo.getUserInfo(request);
        
        String action = request.getParameter("action");
        if(action == null)
            action = "accueil";            
        else if(action.equals("recherche")){
            if( !userinfo.isLogged()){
                Random rand = new Random();
                int id = rand.nextInt(42);
                userinfo.setId(id);
                userinfo.setLogged(true);
            }
            Vues.redirect(request, response, "DisplayResearch");
        }
        else if(action.equals("details")){
            Vues.redirect(request, response, "DetailsOfFilm");
        }
        else if(action.equals("caddie"))
        {
            request.setAttribute("session", userinfo.getId());
            Vues.redirect(request, response, "caddie");
        }
        else if(action.equals("commander"))
        {
            try {
                int idsale = Integer.parseInt(request.getParameter("salle"));
                int sceance = Integer.parseInt(request.getParameter("sceance"));
                int idfilm =  Integer.parseInt(request.getParameter("id"));
                String datejour = request.getParameter("datejour");
                
                if( userinfo.commander(idsale, sceance, idfilm, datejour, 0, 1))
                {
                    request.setAttribute("message", "Ajout au caddie réussit");
                    Vues.redirect(request, response, "Erreur");
                }
                else
                {
                    request.setAttribute("message", 
                            "Erreur: impossible d'ajouter au caddie");
                    Vues.redirect(request, response, "Erreur");
                }
            } catch(NumberFormatException e) {
                request.setAttribute("message",
                        "Erreur: Numero de chambre invalide");
            }
        }
        else if(action.equals("payer"))
        {
            request.setAttribute("session", userinfo.getId());
            Vues.redirect(request, response, "payement");
        }
        else if(action.equals("annuler"))
        {
            request.setAttribute("session", userinfo.getId());
            Vues.redirect(request, response, "payement");
        }
        
        
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
