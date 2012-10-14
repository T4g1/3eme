/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author T4g1
 */
public class Vues {
    /**
     * Affiche la page de login echoué
     * 
     * @param out   Sortie du code HTML
     */
    public static void showLoginFailed(PrintWriter out) {
        out.println("<p>Login échoué</p>");
    }
    
    /**
     * Affiche une erreur d'ajout d'utilisateur
     * 
     * @param out   Sortie du code HTML
     */
    public static void showAddUserFailed(PrintWriter out) {
        out.println("<p>Ajout d'utilisateur impossible, nom déjà prit</p>");
    }
    
    /**public static void showLoginError(PrintWriter out, HttpServletRequest request)
    {
        
        out.println("<p>Une erreur c'est produite, nous ne parvenons pas à vous identifiez</p>");
        out.println("<p>nombre de parametre reçus: </p>");
        out.println("<p>action = "+request.getParameter("action")+ "</p>");
        out.println("<p>username = "+request.getParameter("username")+ "</p>");
        out.println("<p>password = "+request.getParameter("password")+ "</p>");
        
    }*/
    
    /**
     * Ajoute un message sur la page
     * 
     * @param out           La page sur laquelle on affiche
     * @param message       Message désiré
     */
    public static void addMessage(PrintWriter out, String message) {
        out.println("<p>Trace Message: "+message+ "</p>");    
    }
    
    /**
     * Place les entête HTML du début de la page
     * 
     * @param out           La page sur laquelle on affiche
     */
    public static void begin(PrintWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");        
    }
    
    /**
     * Place les fermetures des balises HTML pour terminer la
     * génération de la page.
     * 
     * @param out           La page sur laquelle on affiche
     */
    public static void end(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");        
    }
    
    /**
     * Redirige sur la page donnée
     * 
     * @param request       Requete recue
     * @param response      Reponse recue
     * @param page          Page voulue
     */
    public static void redirect(
            HttpServletRequest request, HttpServletResponse response, 
            String page) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page + ".jsp");
        rd.forward(request, response);
    }
    
    /**
     * Donne le titre de la page donnée
     * 
     * @param page          Page dont on veut le titre
     * 
     * @return              Titre de la page
     */
    public static String getPageTitle(String page) {
        return "Mon super site de vacance";
    }
}
