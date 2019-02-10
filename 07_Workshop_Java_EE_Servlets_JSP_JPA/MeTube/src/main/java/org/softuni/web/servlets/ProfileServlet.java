package org.softuni.web.servlets;

import org.softuni.service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends BaseServlet {

    private final TubeService tubeService;

    @Inject
    public ProfileServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("myTubes", this.tubeService
                .listUserTubes(
                        this.getLoggedInUser(req).getUsername()
                ));
        this.renderPage(req, resp, "/WEB-INF/jsp/profile.jsp");
    }
}
