/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.io.PrintWriter;

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
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login: Refusé</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Login échoué</p>");
        out.println("</body>");
        out.println("</html>");
    }
    
    /**
     * Affiche la page de login réussit
     * 
     * @param out   Sortie du code HTML
     */
    public static void showLoginSuccess(PrintWriter out, String username)
    {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login: Accepté</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Login réussit</p>");
        out.println("<p>Bienvenue, " + username + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
