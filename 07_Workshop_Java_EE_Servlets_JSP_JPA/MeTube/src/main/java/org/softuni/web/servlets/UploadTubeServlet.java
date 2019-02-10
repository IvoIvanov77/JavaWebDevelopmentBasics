package org.softuni.web.servlets;

import org.softuni.domain.dto.CreateTubeDto;
import org.softuni.service.TubeService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/upload")
public class UploadTubeServlet extends BaseServlet {

    private final TubeService tubeService;

    @Inject
    public UploadTubeServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.renderPage(req, resp, "/WEB-INF/jsp/upload-tube.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateTubeDto createTubeDto = (CreateTubeDto) req.getAttribute("model");
        this.tubeService.createTube(createTubeDto,
                this.getLoggedInUser(req).getUsername());
        this.redirect(resp, "/home");
    }
}
