package Servlet;

import Session.UserInfo;
import Vues.Vues;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet gérant la navigation sur le site une fois loggé
 * 
 * @author T4g1
 */
public class controlServlet extends HttpServlet {
    private UserInfo userinfo;

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
        
        // Vérifie l'action
        String action = request.getParameter("action");
        if(action == null) action = "acceuil";
        
        if(action.equals("listing"))
        {
            userinfo.setPage("listing");
        }
        else if(action.equals("logoff"))
        {
            userinfo.cancelCaddie();
            userinfo.setLogged(false);
            
            userinfo.setPage("index");
        }
        else if(action.equals("commander"))
        {
            try {
                int numero = Integer.parseInt(request.getParameter("numero"));
            /*
                if(userinfo.commander(numero))
                {
                    request.setAttribute("message", "Ajout au caddie réussit");
                }
                else
                {
                    request.setAttribute("message", 
                            "Erreur: Impossible d'ajouter cette chambre");
                }
            */
            } catch(NumberFormatException e) {
                request.setAttribute("message",
                        "Erreur: Numero de chambre invalide");
            }
        }
        else if(action.equals("caddie"))
        {
            userinfo.setPage("caddie");
        }
        else if(action.equals("payer"))
        {
            if(userinfo.payerCaddie())
            {
                request.setAttribute("message", 
                            "Caddie payé");
            }
            else
            {
                request.setAttribute("message", 
                            "Erreur: Payement impossible");
            }
            
            userinfo.setPage("caddie");
        }
        // Annule les commandes en cours non payées
        else if(action.equals("annuler"))
        {
            if(userinfo.cancelCaddie())
            {
                request.setAttribute("message", 
                            "Caddie annulé");
            }
            else
            {
                request.setAttribute("message", 
                            "Erreur: Annulation impossible");
            }
            
            userinfo.setPage("caddie");
        }
        else
        {
            userinfo.setPage("userpanel");
        }
        
        Vues.redirect(request, response, "index");
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
