package org.softuni.web.servlets;

import org.softuni.domain.service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/all")
public class AllTubesServlet extends HttpServlet {

    private final TubeService tubeService;

    @Inject
    public AllTubesServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allTubes", this.tubeService.listAllTubes());
        req.getRequestDispatcher("/jsp/all-tubes.jsp").forward(req, resp);
    }
}
