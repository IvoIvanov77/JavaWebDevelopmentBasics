package org.softuni.web.servlets;

import org.softuni.domain.dto.LoggedInUserInfoDto;
import org.softuni.domain.dto.LoginUserDto;
import org.softuni.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends BaseServlet{
    private final UserService userService;

    @Inject
    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.renderPage(req, resp, "/WEB-INF/jsp/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginUserDto userBindingModel = (LoginUserDto) req.getAttribute("model");
        LoggedInUserInfoDto userInfo = this.userService.loginUser(userBindingModel);
        if(userInfo != null){
            req.getSession().setAttribute("user", userInfo);
            this.redirect(resp, "/home");
        }else{
            this.redirect(resp, "/login");
        }
    }
}
