<%if(request.getSession().getAttribute("user") != null){%>
    <jsp:include page="/WEB-INF/jsp/fragments/logged_user_navigation.jsp"/>
<%}else {%>
    <jsp:include page="/WEB-INF/jsp/fragments/guest_navigation.jsp"/>
<%}%>

