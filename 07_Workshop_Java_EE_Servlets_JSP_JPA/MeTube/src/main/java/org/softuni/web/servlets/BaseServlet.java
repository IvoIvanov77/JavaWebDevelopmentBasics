package org.softuni.web.servlets;

import org.softuni.domain.dto.LoggedInUserInfoDto;
import org.softuni.domain.dto.LoginUserDto;
import org.softuni.service.TubeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected BaseServlet(){
    }

    protected void renderPage(HttpServletRequest req, HttpServletResponse resp, String view)
            throws ServletException, IOException {
        req.setAttribute("page", view);
        req.getRequestDispatcher("/WEB-INF/jsp/base-layout.jsp").forward(req, resp);
    }

    protected void redirect(HttpServletResponse resp, String rout)
            throws IOException {
        resp.sendRedirect(rout);
    }

    protected LoggedInUserInfoDto getLoggedInUser(HttpServletRequest req){
        return (LoggedInUserInfoDto) req.getSession().getAttribute("user");
    }
}
