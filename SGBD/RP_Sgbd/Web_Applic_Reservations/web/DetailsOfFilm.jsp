<%-- 
    Document   : DetailsOfFilm
    Created on : 05-déc.-2012, 17:02:06
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
            String html = Vues.Vues.getFilmInfo(request, response);
            %><%= html %>
            <br/><br/>
            <%@include file="bottom.jsp" %>
        </div>
    </body>
</html>
