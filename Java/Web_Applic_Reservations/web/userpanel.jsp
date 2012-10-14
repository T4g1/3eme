<%-- 
    Document   : userpanel
    Created on : 11-oct.-2012, 15:20:21
    Author     : T4g1
--%>

<%
// Verifie que l'utilisateur est loggé
Session.UserInfo userinfo = (Session.UserInfo)
        session.getAttribute(loginServlet.USER_INFO_KEY);
if(!userinfo.isLogged())
{
    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
    rd.forward(request, response);
}
%>
<%@page import="Servlet.loginServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pannel d'utilisateur</title>
    </head>
    <body>
        <%
        // Regarde quelle page est demandée
        String where = (String)request.getParameter("where");
        if(where == null)
        {
            where = "acceuil";
        }
        
        // Inclut la page demandée
        if(where.equals("listing"))
        {
            %><%@ include file="includes/listing.jsp" %><%
        }
        else if(where.equals("logoff"))
        {
            // Vide le caddie
            
            // Se déconnecte
            userinfo.setLogged(false);
            
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
        else
        {
            %><%@ include file="includes/acceuil.jsp" %><%
        }
        %>
    </body>
</html>
