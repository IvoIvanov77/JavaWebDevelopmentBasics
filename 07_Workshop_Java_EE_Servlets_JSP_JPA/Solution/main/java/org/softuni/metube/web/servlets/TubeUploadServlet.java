package org.softuni.metube.web.servlets;

import org.softuni.metube.domain.Tube;
import org.softuni.metube.domain.TubeStatus;
import org.softuni.metube.domain.User;
import org.softuni.metube.repository.TubeRepository;
import org.softuni.metube.repository.TubeRepositoryImpl;
import org.softuni.metube.repository.UserRepositoryImpl;
import org.softuni.metube.service.RequestBodyParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/upload")
public class TubeUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Tube tube = RequestBodyParser.parseRequestBody(req.getParameterMap(), Tube.class);

        tube.setStatus(TubeStatus.Pending);

        new TubeRepositoryImpl().save(tube);
        new TubeRepositoryImpl().setUploader(tube.getId(),
                (String) req.getSession().getAttribute("user-id"));

        resp.sendRedirect("/home");
    }
}
