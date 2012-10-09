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
    public static void addLoginSuccess(PrintWriter out, String username)
    {
        out.println("<p>Login réussit</p>");
        out.println("<p>Bienvenue, " + username + "</p>");
        
    }
    
    public static void addLoginError(PrintWriter out, HttpServletRequest request)
    {
        
        out.println("<p>Une erreur c'est produite, nous ne parvenons pas à vous identifiez</p>");
        out.println("<p>nombre de parametre reçus: </p>");
        out.println("<p>action = "+request.getParameter("action")+ "</p>");
        out.println("<p>username = "+request.getParameter("username")+ "</p>");
        out.println("<p>password = "+request.getParameter("password")+ "</p>");
        
    }
    
    
    
    public static void addmessage(PrintWriter out, String message){
        out.println("<p>Trace Message: "+message+ "</p>");    
    }
    
    public static void addHtmlHeader(PrintWriter out){
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");        
    
    }
    
    public static void addHtmlBottom(PrintWriter out){
        out.println("</body>");
        out.println("</html>");        
    
    }
    
}
