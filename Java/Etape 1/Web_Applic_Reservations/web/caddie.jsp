<%@page import="Session.UserInfo"%>
<h1>Contenu du caddie</h1>

<%
UserInfo userinfo = (UserInfo)session.getAttribute(UserInfo.USER_INFO_KEY);
String html = Vues.Vues.getCaddieContent(userinfo.getCaddie());
%><%= html %>

<br />
<a href="controlServlet?action=payer">Payer</a><br />
<a href="controlServlet?action=annuler">Annuler</a><br />
