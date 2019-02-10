package org.softuni.web.servlets;

import org.softuni.domain.dto.TubeDetailsDto;
import org.softuni.service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/details/*")
public class TubeDetailsServlet extends BaseServlet {

    private final TubeService tubeService;

    @Inject
    public TubeDetailsServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tubeId = (String) req.getAttribute("tubeId");
        TubeDetailsDto tubeDetailsDto = this.tubeService.tubeDetails(tubeId);
        req.setAttribute("tubeDetails", tubeDetailsDto);
        this.renderPage(req, resp, "/WEB-INF/jsp/details.jsp");

    }
}
