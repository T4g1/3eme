<%-- 
    Document   : Erreur
    Created on : 09-déc.-2012, 14:27:49
    Author     : freddy
--%>
<%@page import="Session.UserInfo"%>
<%
UserInfo userinfo = (UserInfo)session.getAttribute(UserInfo.USER_INFO_KEY);
if(userinfo == null) userinfo = new UserInfo();
if(!userinfo.isLogged())
    userinfo.setPage("login");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div id="bloc_page">
            <%@include file="header.jsp" %>
            <%
                String message = request.getAttribute("message").toString();
                %>
                <h1>Rapport d'erreur</h1>
                <p><%=message%></p>
            <br/><br/>
            <%@include file="bottom.jsp" %>
        </div>        
    </body>
</html>
