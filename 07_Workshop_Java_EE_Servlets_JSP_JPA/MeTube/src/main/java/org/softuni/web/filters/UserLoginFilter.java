package org.softuni.web.filters;

import org.softuni.domain.dto.LoginUserDto;
import org.softuni.domain.dto.RegisterUserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class UserLoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getMethod().equalsIgnoreCase("post")){
            LoginUserDto userDto = new LoginUserDto();
            userDto.setUsername(request.getParameter("username"));
            userDto.setPassword(request.getParameter("password"));

            request.setAttribute("model", userDto);
        }

        filterChain.doFilter(request, response);

    }
}
