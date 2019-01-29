
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <%if(request.getSession().getAttribute("username") == null){%>
        <jsp:include page="guest_navigation.jsp"/>
    <%}else{
        if((Boolean) request.getSession().getAttribute("isAdmin")){%>
            <jsp:include page="admin_navigation.jsp"/>
        <%}else {%>
            <jsp:include page="user_navigation.jsp"/>
        <%}
    }%>
</header>



