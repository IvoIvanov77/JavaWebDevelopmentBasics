<%@ page import="data.Cat" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Cats</title>
</head>
<body>
    <jsp:include page="../common/app_navigation.jsp"/>

    <%Collection<Cat> allCats = (Collection<Cat>) request.getAttribute("allCats");%>

    <h1>All Cats!</h1>
    <hr>

    <%if(allCats.isEmpty()){%>
        <h3>There are no cats. <a href="/cats/create">Create Some!</a></h3>
    <%}else{
        for (Cat cat : allCats) {%>
            <p><a href="/cats/profile?name=<%=cat.getName()%>"><%=cat.getName()%></a></p>
        <%}
    }%>
    <p><a href="/">Back To Home</a></p>

</body>
</html>
