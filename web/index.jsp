<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page import="com.work.entity.City" %>
<%--
  Created by IntelliJ IDEA.
  User: samoilovich_y
  Date: 30.11.2018
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show All Cities</title>
</head>
<body>
<form action="insert.jsp" method="post">
    <button type="submit">Insert</button>
</form>
<form action="controller?action=/validateFindByName" method="post">
    <input type="text" name="cityName" placeholder="Minsk">
    <button type="submit">Find</button>
</form>
<table border="1">
    <thead>
    <tr>
        <th>City Name</th>
        <th>Population</th>
        <th>Country Code</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <c:forEach items="${cities}" var="cityName">
        <tr>
            <td><c:out value="${cityName.getName()}" /></td>
            <td><c:out value="${cityName.getPopulation()}" /></td>
            <td><c:out value="${cityName.getCountryCode()}" /></td>
            <td><form action="update.jsp?cityId=${cityName.getId()}&population=${cityName.getPopulation()}" method="post"><button type="submit">
                Update</button></form></td>
            <td><form action="controller?action=/delete&cityId=${cityName.getId()}" method="post"><button type="submit">
                Delete</button></form></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
