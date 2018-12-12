<%--
  Created by IntelliJ IDEA.
  User: samoilovich_y
  Date: 07.12.2018
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Validation Failed</title>
</head>
<% if (request.getParameter("action") == "error"){%>
<% String string = (String)request.getAttribute("populationNotDigital");%>
<% if(string!= null){%>
<h1><%=string%></h1>
<%}%>
<% string = (String)request.getAttribute("populationNotValidate");%>
<% if(string!= null){%>
<h1><%=string%></h1>
<%}%>
<% string = (String)request.getAttribute("populationNull");%>
<% if(string!= null){%>
<h1><%=string%></h1>
<%}%>
<%}%>
<h1><c:out value="${popopulationNotDigital}" /></h1>
<c:out value="${}" />
<form action="update.jsp" method="post">
    <button type="submit">Try again</button>
</form>
<form action="controller" method="post">
    <button type="submit">Go to the main page</button>
</form>
</body>
</html>
