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
import java.util.List;

@WebServlet("/all")
public class AllUsersServlet extends HttpServlet {

    private final UserRepository userRepository;

    private final Reader htmlReader;

    @Inject
    public AllUsersServlet(UserRepository userRepository, Reader htmlReader) {
        this.userRepository = userRepository;
        this.htmlReader = htmlReader;
    }

    private String renderTableRow(User user, int number){
        return String.format("<tr>" +
                "      <th scope='row'>%d</th>" +
                "      <td>%s</td>" +
                "      <td>%s</td>" +
                "      <td>" +
                "           <a href='#'>edit</a>" +
                "           <a href='/delete?id=%s'>delete</a>" +
                "           <a href='/'>home</a>" +
                "      </td>" +
                "    </tr>",
                number, user.getName(), user.getEmail(), user.getId());
    }
    
    private String renderAllUsers(List<User> allUsers){
        StringBuilder rows = new StringBuilder();
        int number = 1;
        for (User user : allUsers) {
            rows.append(this.renderTableRow(user, number++));

        }
        return rows.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = this.userRepository.findAll();
        String htmlFileContent = this.htmlReader.read(HTMLPaths.ALL_USERS_HTML);
        String formatedHtmlContent = String.format(htmlFileContent, this.renderAllUsers(allUsers));
        resp.getWriter().println(formatedHtmlContent);
    }
}
