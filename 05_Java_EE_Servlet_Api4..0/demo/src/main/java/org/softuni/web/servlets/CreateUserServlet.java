package org.softuni.web.servlets;

import org.softuni.domain.model.User;
import org.softuni.domain.repositories.UserRepository;
import org.softuni.web.constants.HTMLPaths;
import org.softuni.web.io.Reader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/create")
public class CreateUserServlet extends HttpServlet {

    private final UserRepository userRepository;

    private final Reader htmlReader;

    @Inject
    public CreateUserServlet(UserRepository userRepository, Reader htmlReader) {
        this.userRepository = userRepository;
        this.htmlReader = htmlReader;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlContent = this.htmlReader.read(HTMLPaths.CREATE_USER_HTML);
        resp.getWriter().println(htmlContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setEmail(req.getParameter("email"));
        user.setName(req.getParameter("name"));
        this.userRepository.saveUser(user);
        resp.sendRedirect("/all");
    }
}
