<%-- 
    Document   : index
    Created on : 30 sept. 2012, 23:14:16
    Author     : T4g1
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
        <title><%= userinfo.getPageTitle() %></title>
    </head>
    <body>
        <div id="bloc_page">
            <%@include file="header.jsp" %>
            <%@include file="corps.jsp" %>
            <%@include file="bottom.jsp" %>
        </div>
        <%
        String html = Vues.Vues.getListing();
        %><%= html %>
    </body>
</html>
