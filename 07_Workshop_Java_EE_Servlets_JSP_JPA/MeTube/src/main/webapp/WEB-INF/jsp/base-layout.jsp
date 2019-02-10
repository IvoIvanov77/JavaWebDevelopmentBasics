<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>MeTube&trade;</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
<div class="container-fluid">
    <%String view = (String) request.getAttribute("page");%>
    <jsp:include page="/WEB-INF/jsp/fragments/navigation.jsp"/>
    <main>
        <jsp:include page= '<%=view%>'/>
    </main>
    <jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</div>
</body>
</html>
