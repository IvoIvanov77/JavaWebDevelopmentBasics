<%@ page import="data.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Orders</title>
</head>
<body>
    <jsp:include page="../common/app_navigation.jsp"/>

    <%List<Order> allOrders = (List<Order>) request.getAttribute("allOrders");%>

    <h1>All Orders</h1>
    <hr>

    <%if(allOrders.isEmpty()){%>
        <h3>There are no orders.</h3>
    <%}else{%>
    <table >
        <tr>
            <th>Client name</th>
            <th>Cat name</th>
            <th>Made on</th>
        </tr>
        <%for (Order order : allOrders) {%>
            <tr>
                <td><%=order.getClient().getUsername()%></td>
                <td><%=order.getCat().getName()%></td>
                <td><%=order.getFormatedDate()%></td>
            </tr>
        <%}%>
    </table>
    <%}%>
    <p><a href="/">Back To Home</a></p>

</body>
</html>
