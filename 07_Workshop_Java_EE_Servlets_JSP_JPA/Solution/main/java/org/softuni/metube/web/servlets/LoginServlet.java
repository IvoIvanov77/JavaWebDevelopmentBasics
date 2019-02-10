package org.softuni.metube.web.servlets;

import org.softuni.metube.domain.User;
import org.softuni.metube.repository.UserRepositoryImpl;
import org.softuni.metube.service.RequestBodyParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setHeader("Content-Type", "text/html");

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map params = RequestBodyParser.parseRequestBody(
                req.getParameterMap(),
                Map.class
        );

        User user = new UserRepositoryImpl()
                .findByUsername((String) params.get("username"));

        if(user == null || !user.getPassword().equals(params.get("password"))) {
            resp.sendRedirect("/login");
        }

        req.getSession().setAttribute("user-id", user.getId());
        req.getSession().setAttribute("user-username", user.getUsername());
        req.getSession().setAttribute("user-email", user.getEmail());
        req.getSession().setAttribute("user-role", user.getRole().toString());

        resp.sendRedirect("/home");
    }
}
