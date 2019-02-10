package org.softuni.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tube/details/*")
public class TubeDetailsFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String[] requestPath = request.getRequestURI().split("/");
        String pathVariable = requestPath[requestPath.length - 1];
        request.setAttribute("tubeId", pathVariable);

        filterChain.doFilter(request, response);

    }
}
