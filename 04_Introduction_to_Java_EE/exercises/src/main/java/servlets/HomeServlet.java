package servlets;

import util.CatRepositoryInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        CatRepositoryInitializer.initialize(this.getServletContext());

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<h1>Welcome to Fluffy Duffy Munchkin Cats!<h1>")
                .append("<h3>Navigate through the application using the links below!</h3>")
                .append("<hr>")
                .append("<p><a href='/cats/create'>Create Cat</a></p>")
                .append("<p><a href='/cats/all'>All Cats</a></p>");

        out.println(htmlContent.toString());

    }
}
