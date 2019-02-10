package org.softuni.web.servlets;

import org.softuni.domain.dto.RegisterUserDto;
import org.softuni.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends BaseServlet {

    private final UserService userService;

    @Inject
    public RegisterServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.renderPage(req, resp, "/WEB-INF/jsp/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegisterUserDto userDto = (RegisterUserDto) req.getAttribute("model");
        if(this.userService.registerUser(userDto)){
            this.redirect(resp, "/login");
        }else{
            this.redirect(resp, "/register");
        }

    }
}
