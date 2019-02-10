<%@ page import="org.softuni.domain.dto.ListTubesDto" %>
<%@ page import="org.softuni.domain.dto.LoggedInUserInfoDto" %>
<%@ page import="java.util.List" %>
<hr class="my-2"/>
<div class="text-center mt-3">
    <%LoggedInUserInfoDto user = (LoggedInUserInfoDto) request.getSession().getAttribute("user");%>
    <h4 class="h4 text-info">Welcome, <%=user.getUsername()%></h4>
</div>
<hr class="my-4">
<div class="container d-flex justify-content-around">
    <%List<ListTubesDto> allTubesList = (List<ListTubesDto>) request.getAttribute("allTubes");%>
    <%for (ListTubesDto t : allTubesList) {%>
         <div  class="w-25 p-3">
             <a href="tube/details/<%=t.getId()%>">
                 <img src="https://img.youtube.com/vi/<%=t.getYouTubeId()%>/3.jpg"/>
                 <p><%=t.getYouTubeId()%></p>
                 <p><%=t.getTitle()%></p>
                 <p><%=t.getAuthor()%></p>
             </a>

         </div>
    <%}%>
</div>

<hr class="my-3"/>