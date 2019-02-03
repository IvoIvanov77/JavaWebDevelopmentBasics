package org.softuni.web.servlets;

import org.softuni.domain.service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class CreateTubeServlet extends HttpServlet {

    private final TubeService tubeService;

    @Inject
    public CreateTubeServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.tubeService.createTube(
                req.getParameter("title"),
                req.getParameter("description"),
                req.getParameter("youTubeLink"),
                req.getParameter("uploader")
        );
        resp.sendRedirect("/tubes/all");
    }
}
