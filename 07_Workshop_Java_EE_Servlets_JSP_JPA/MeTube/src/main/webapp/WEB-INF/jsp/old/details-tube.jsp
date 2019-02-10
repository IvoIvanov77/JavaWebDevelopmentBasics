<%@ page import="org.softuni.domain.dto.TubeDetailsDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>
<div class="jumbotron text-center" <%TubeDetailsDto detailsDto =
        (TubeDetailsDto) request.getAttribute("tubeDetails");%>>
    <%if(detailsDto == null){%>
    <h3>No such Tube exists</h3>
    <%}else{%>
    <h1><%=detailsDto.getTitle()%></h1>
    <hr class="my-4">
    <p><%=detailsDto.getDescription()%></p>
    <hr class="my-4">
    <a href="<%=detailsDto.getYouTubeLink()%>">Link to Video</a>
    <span><%=detailsDto.getUploader()%></span>
    <%}%>
    <hr class="my-4">
    <a href="/">back to home</a>
</div>
</body>
</html>
