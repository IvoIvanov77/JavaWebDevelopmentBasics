<%@ page import="data.Cat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>profile</title>
</head>
<body>
<jsp:include page="../common/app_navigation.jsp"/>
    <%Cat cat = (Cat) request.getAttribute("cat");%>
    <%if(cat == null){%>
        <h1>Cat, with name- <%=request.getParameter("name")%> was not found.</h1>
    <%}else{%>
        <h1>Cat - <%=cat.getName()%> from jsp</h1><hr>
        <h3>Breed: <%=cat.getBreed()%></h3>
        <h3>Color:<%=cat.getColor()%></h3>
        <h3>Number of legs: <%=cat.getNumberOfLegs()%></h3>
        <h3>Number of views: <%=cat.getViews()%></h3>
        <form method="post" action="/order/create?cat=<%=cat.getName()%>">
            <input type="submit" value="Order"\>
        </form>
        <hr>
    <%}%>
    <p><a href="/cats/all">Back</a></p>

</body>
</html>
