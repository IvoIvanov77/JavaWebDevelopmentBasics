package opg.softuni.panda.web.filters;


import opg.softuni.panda.domain.entities.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/faces/jsf/user/admin/*", "/jsf/user/admin/*"})
public class UserAdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if (session == null || session.getAttribute("userRole") != Role.ADMIN) {
            resp.sendRedirect("/jsf/guest/login.xhtml");
        } else {
            chain.doFilter(req, resp);
        }

    }
}
