package org.softuni.metube.web.servlets;

import org.softuni.metube.repository.TubeRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/tube/status")
public class AdminTubeStatus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status") + "d";
        String tubeId = req.getParameter("id");

        new TubeRepositoryImpl().updateStatus(tubeId, status);

        resp.sendRedirect("/admin/tube/pending");
    }
}
