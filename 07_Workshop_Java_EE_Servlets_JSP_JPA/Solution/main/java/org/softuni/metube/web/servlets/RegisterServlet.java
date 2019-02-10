package org.softuni.metube.web.servlets;

import org.modelmapper.ModelMapper;
import org.softuni.metube.domain.User;
import org.softuni.metube.domain.UserRole;
import org.softuni.metube.repository.UserRepositoryImpl;
import org.softuni.metube.service.RequestBodyParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setHeader("Content-Type", "text/html");

        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = RequestBodyParser.parseRequestBody(req.getParameterMap(), User.class);

        if(new UserRepositoryImpl().findAll().size() > 0) user.setRole(UserRole.User);
        else user.setRole(UserRole.Admin);

        new UserRepositoryImpl().save(user);

        resp.sendRedirect("/login");
    }
}
