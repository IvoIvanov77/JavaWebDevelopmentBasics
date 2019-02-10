<%@ page import="org.softuni.domain.dto.ListTubesDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>
<div class="jumbotron text-center">
    <h1>All Tubes</h1>
    <hr class="my-4">
    <h3>Check our tubes bellow.</h3>
    <hr class="my-4">
    <div <%List<ListTubesDto> allTubes = (List<ListTubesDto>)request.getAttribute("allTubes");%>>
        <%if(allTubes.isEmpty()){%>
        <p>No tubes – <a href="/tubes/create">Create some</a></p>
        <%}else{%>
        <ul class="col-md-3 mx-auto">
            <% for (ListTubesDto dto : allTubes) {%>
            <li><a href="/tubes/details?name=<%=dto%>"><%=dto%></a></li>
            <%}%>
        </ul>
        <%}%>
    </div>

    <hr class="my-4">
    <a href="/">back to home</a>
</div>
</body>
</html>
