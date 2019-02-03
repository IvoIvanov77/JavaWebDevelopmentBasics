package org.softuni.web.servlets;

import org.softuni.web.constants.HTMLPaths;
import org.softuni.web.io.Reader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private final Reader htmlReader;

    @Inject
    public HomeServlet(Reader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlContent = this.htmlReader.read(HTMLPaths.INDEX_HTML);
        resp.getWriter().println(htmlContent);
    }
}
