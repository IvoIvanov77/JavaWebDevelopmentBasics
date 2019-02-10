package org.softuni.web.servlets;

import org.softuni.service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends BaseServlet {

    private final TubeService tubeService;

    @Inject
    public HomeServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allTubes", this.tubeService.listAllTubes());
        this.renderPage(req, resp, "/WEB-INF/jsp/home.jsp");
    }
}
