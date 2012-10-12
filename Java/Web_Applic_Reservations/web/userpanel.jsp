<%-- 
    Document   : userpanel
    Created on : 11-oct.-2012, 15:20:21
    Author     : T4g1
--%>

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
        Session.UserInfo userinfo = (Session.UserInfo)
                session.getAttribute(loginServlet.USER_INFO_KEY);
        if(!userinfo.isLogged())
        {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
        %>
        
        <h1>Authentification réussie !</h1>
    </body>
</html>
