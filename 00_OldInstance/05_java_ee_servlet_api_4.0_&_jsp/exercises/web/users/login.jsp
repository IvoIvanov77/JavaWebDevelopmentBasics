
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<p style="color: red"><%=request.getAttribute("errorMessage") == null ? ""
        : request.getAttribute("errorMessage")%></p>
<form action="/users/login" method="post">
    Username: <input type="text" name="username"/>
    <br/>
    Password: <input type="text" name="password"/>
    <br/>
    <input type="submit" value="Login"/>
</form>
<p><a href="/">Back To Home</a></p>
<p><a href="/users/register">Register</a></p>

</body>
</html>
