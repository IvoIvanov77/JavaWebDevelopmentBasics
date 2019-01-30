package org.softuni.servlets;

import org.softuni.util.HtmlRider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private static final String INDEX_HTML_FILE_PATH = "C:\\Users\\Ivaylo\\Documents\\SoftUni\\" +
            "JavaWeb2019\\JavaWebDevelopmentBasics\\04_Introduction_to_Java_EE\\demo\\src\\main" +
            "\\resources\\html\\index.html";

    private final HtmlRider htmlRider;

    @Inject
    public HomeServlet(HtmlRider htmlRider) {
        this.htmlRider = htmlRider;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String htmlContent = this.htmlRider.readHtmlFile(INDEX_HTML_FILE_PATH);
        writer.println(htmlContent);
        writer.println("<h1>Hello from Home servlet</h1>");
    }
}
