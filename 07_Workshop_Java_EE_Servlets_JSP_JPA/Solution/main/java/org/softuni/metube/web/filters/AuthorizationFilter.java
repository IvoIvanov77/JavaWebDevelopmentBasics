package org.softuni.metube.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        filterName = "authFilter",
        urlPatterns = {"/home", "/tube/details", "/tube/upload", "/admin/*"}
)
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String userId = (String) req.getSession().getAttribute("user-id");
        String role = (String) req.getSession().getAttribute("user-role");

        boolean authResult = !(!isAuthorizedUser(req, res) || !isAuthorizedAdmin(req, res));

        if(authResult) {
            chain.doFilter(req, res);
        }
    }

    private boolean isAuthorizedUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userId = (String) req.getSession().getAttribute("user-id");

        if (userId == null) {
            res.sendRedirect("/login");
            return false;
        }

        return true;
    }

    private boolean isAuthorizedAdmin(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userRole = (String) req.getSession().getAttribute("user-role");
        if(req.getRequestURL().toString().startsWith("/admin") && !userRole.equals("Admin")) {
            res.sendRedirect("/home");
            return false;
        }

        return true;
    }
}
