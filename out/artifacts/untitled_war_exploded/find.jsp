<%--
  Created by IntelliJ IDEA.
  User: samoilovich_y
  Date: 10.12.2018
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Find</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>City Name</th>
        <th>Population</th>
        <th>Country Code</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <c:forEach items="${city}" var="cityName">
        <tr>
            <td><c:out value="${cityName.getName()}" /></td>
            <td><c:out value="${cityName.getPopulation()}" /></td>
            <td><c:out value="${cityName.getCountryCode()}" /></td>
            <td><form action="update.jsp?cityId=${cityName.getId()}&cityName=${cityName.getName()}&population=${cityName.getPopulation()}" method="post"><button type="submit">
                Update</button></form></td>
            <td><form action="controller?action=/delete&cityId=${cityName.getId()}" method="post"><button type="submit">
                Delete</button></form></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
