/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

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
    public static void showLoginFailed(PrintWriter out)
    {
        
        out.println("<p>Login échoué</p>");
    }
    
    /**
     * Affiche la page de login réussit
     * 
     * @param out   Sortie du code HTML
     */
    public static void showLoginSuccess(PrintWriter out, String username)
    {
        out.println("<p>Login réussit</p>");
        out.println("<p>Bienvenue, " + username + "</p>");
        
    }
    
    /**
     * Affiche une erreur d'ajout d'utilisateur
     * 
     * @param out   Sortie du code HTML
     */
    public static void showAddUserFailed(PrintWriter out)
    {
        out.println("<p>Ajout d'utilisateur impossible, nom déjà prit</p>");
        
    }
    
    public static void showLoginError(PrintWriter out, HttpServletRequest request)
    {
        
        out.println("<p>Une erreur c'est produite, nous ne parvenons pas à vous identifiez</p>");
        out.println("<p>nombre de parametre reçus: </p>");
        out.println("<p>action = "+request.getParameter("action")+ "</p>");
        out.println("<p>username = "+request.getParameter("username")+ "</p>");
        out.println("<p>password = "+request.getParameter("password")+ "</p>");
        
    }
    
    
    
    public static void addMessage(PrintWriter out, String message){
        out.println("<p>Trace Message: "+message+ "</p>");    
    }
    
    public static void begin(PrintWriter out){
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");        
    
    }
    
    public static void end(PrintWriter out){
        out.println("</body>");
        out.println("</html>");        
    
    }
    
}
