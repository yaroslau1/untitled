<%--
  Created by IntelliJ IDEA.
  User: samoilovich_y
  Date: 30.11.2018
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Show All Cities</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>City Name/th>
        <th>Population</th>
        <th>Country Code</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cities}" var="cityName">
        <tr>
            <td><c:out value="${cityName.getName()}" /></td>
            <td><c:out value="${cityName.getPopulation()}" /></td>
            <td><c:out value="${cityName.getCountryCode()}" /></td>
            <td><a href="controller?action=edit&userId=<c:out value="${cityName.getId()}"/>">Update</a></td>
            <td><a href="controller?action=delete&userId=<c:out value="${cityName.getId()}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="controller?action=insert">Add User</a></p>
</body>
</html>
