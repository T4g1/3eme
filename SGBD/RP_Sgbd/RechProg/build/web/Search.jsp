<%-- 
    Document   : Search
    Created on : 01-déc.-2012, 11:59:19
    Author     : freddy
--%>

<%@page import="Vues.newpackage.Vues"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String html = Vues.getListing();
        %><%= html %>
    </body>
</html>
