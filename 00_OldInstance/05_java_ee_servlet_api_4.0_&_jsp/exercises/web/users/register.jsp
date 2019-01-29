
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<p style="color: red"><%=request.getAttribute("errorMessage") == null ? ""
        : request.getAttribute("errorMessage")%></p>
<form action="/users/register" method="post">
    Username: <input type="text" name="username"/>
    <br/>
    Password: <input type="text" name="password"/>
    <br/>
    Confirm password: <input type="text" name="confirm"/>
    <br/>
    <input type="submit" value="Register"/>
</form>
<p><a href="/">Back To Home</a></p>
<p><a href="/users/login">Login</a></p>

</body>
</html>
