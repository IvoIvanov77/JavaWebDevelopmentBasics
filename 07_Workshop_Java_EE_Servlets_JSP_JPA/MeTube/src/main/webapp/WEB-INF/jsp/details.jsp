<%@ page import="org.softuni.domain.dto.TubeDetailsDto" %>
<%
    TubeDetailsDto tubeDetails = (TubeDetailsDto) request.getAttribute("tubeDetails");
%>
<hr class="my-2">
<div class="container-fluid">
    <h2 class="text-center"><%=tubeDetails.getTitle()%></h2>
    <div class="row">
        <div class="col-md-6 my-5">
            <div class="embed-responsive embed-responsive-16by9">
                <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/<%=tubeDetails.getYouTubeId()%>?autoplay=1"
                        allowfullscreen frameborder="0">
                </iframe>

            </div>
        </div>
        <div class="col-md-6 my-5">
            <h1 class="text-center text-info"><%=tubeDetails.getAuthor()%></h1>
            <h3 class="text-center text-info"><%=tubeDetails.getViews()%> Views</h3>
            <div class="h5 my-5 text-center"><%=tubeDetails.getDescription()%></div>
        </div>
    </div>
</div>
<hr class="my-3"/>