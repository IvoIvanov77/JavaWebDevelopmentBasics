package org.softuni.web.filters;

import org.softuni.domain.dto.CreateTubeDto;
import org.softuni.domain.dto.RegisterUserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tube/upload")
public class TubeUploadFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getMethod().equalsIgnoreCase("post")){
            CreateTubeDto tubeDto = new CreateTubeDto();
            tubeDto.setTitle(request.getParameter("title"));
            tubeDto.setAuthor(request.getParameter("author"));
            tubeDto.setDescription(request.getParameter("description"));
            tubeDto.setYouTubeLink(request.getParameter("youtube-link"));

            request.setAttribute("model", tubeDto);
        }

        filterChain.doFilter(request, response);

    }
}
