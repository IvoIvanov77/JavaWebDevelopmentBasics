<%@ page import="org.softuni.domain.dto.LoginUserDto" %>
<%@ page import="org.softuni.domain.dto.ListTubesDto" %>
<%@ page import="java.util.List" %>
<%@ page import="org.softuni.domain.dto.LoggedInUserInfoDto" %><%
    LoggedInUserInfoDto currentUser = (LoggedInUserInfoDto) request.getSession().getAttribute("user");
    List<ListTubesDto> userTubes = (List<ListTubesDto>)request.getAttribute("myTubes");
    int counter = 0;
%>
<hr class="my-2"/>
<div class="text-center mt-3">
    <h4 class="text-info text-center">@<%=currentUser.getUsername()%></h4>
    <h4 class="text-info text-center">(<%=currentUser.getEmail()%>)</h4>
</div>
<hr>
<div class="container-fluid">
    <div class="row d-flex flex-column">
        <table class="table table-hover table-dark">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Author</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%for (ListTubesDto t : userTubes) {%>
            <tr>
                <th scope="col"><%=++counter%></th>
                <td><%=t.getTitle()%></td>
                <td><%=t.getAuthor()%></td>
                <td><a href="tube/details/<%=t.getId()%>">Details</a></td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
<hr class="my-3"/>