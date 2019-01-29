
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create cat</title>
</head>
<body>
<jsp:include page="../common/app_navigation.jsp"/>
<h1>Create a Cat!</h1>
<form action="/cats/create" method="post">
    Name: <input type="text" name="name"/>
    <br/>
    Breed: <input type="text" name="breed"/>
    <br/>
    Color: <input type="text" name="color"/>
    <br/>
    Number of legs: <input type="text" name="legs"/>
    <br/>
    <input type="submit" value="Create Cat"/>
    </form>
<p><a href="/">Back To Home</a></p>

</body>
</html>
