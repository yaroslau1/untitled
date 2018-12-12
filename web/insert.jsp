<%--
  Created by IntelliJ IDEA.
  User: samoilovich_y
  Date: 03.12.2018
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert</title>
</head>
<body>
<form method="POST" action="controller?action=/validateInsert">
    <label for="cityName"<% if (request.getAttribute("actionCityName") == "errorInCityName") {%>class="col-sm-2 control-label error"<%}else{%>class="col-sm-2 control-label"<%}%>>
        City Name :
    </label>
    <input type="text" class="form-control" id="cityName" name="cityName"
           placeholder="Minsk<%if (request.getAttribute("actionCityName") == "errorInCityName") {%>
                <% String string = (String) request.getAttribute("errorCityName");%>
                <% if (string != null) {%>
                <%=string%>
                <%}}%>"> <br/> <br />
    <label for="population"<% if (request.getAttribute("actionPopulation") == "errorInPopulation") {%>class="col-sm-2 control-label error"<%}else{%>class="col-sm-2 control-label"<%}%>>
        Population :
    </label>
    <input type="text" class="form-control" id="population" name="population"
           placeholder="2 000 000<%if (request.getAttribute("actionPopulation") == "errorInPopulation") { String string = (String) request.getAttribute("errorPopulation");%>
            <% if (string != null) {%>
            <%=string%>
                <%}}%>"> <br />
    <label for="countryCode"<% if (request.getAttribute("actionPopulation") == "errorInPopulation") {%>class="col-sm-2 control-label error"<%}else{%>class="col-sm-2 control-label"<%}%>>
        Country Code :
    </label>
    <input type="text" class="form-control" id="countryCode" name="countryCode"
           placeholder="BLR<%if (request.getAttribute("actionCountryCode") == "errorInCountryCode") {%>
                <% String string = (String) request.getAttribute("errorCountryCode");%>
                <% if (string != null) {%>
                <%=string%>
                <%}}%>"> <br />
    <button type="submit">Insert city</button>
</form>

<form method="POST" action="servlet">

    <button type="submit">delete sessions</button>
</form>

</body>
</html>
