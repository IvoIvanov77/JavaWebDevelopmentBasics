package servlets.users;

import data.User;
import enums.UserRole;
import repository.UserRepository;
import util.UserAuthentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/login")
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserAuthentication.getCurrentUser(req, this.getServletContext());
        if(user != null){
            resp.sendRedirect("/");
            return;
        }

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = UserAuthentication.getCurrentUser(req, this.getServletContext());
        if(currentUser != null){
            resp.sendRedirect("/");
            return;
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        if(username.trim().isEmpty() || password.trim().isEmpty()){
            req.setAttribute("errorMessage", "Username and passwords can not be empty.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        UserRepository userRepository = (UserRepository) this.getServletContext()
                .getAttribute("users");
        User user = userRepository.get(username);

        if(user == null || !user.getPassword().equals(password)){
            req.setAttribute("errorMessage", "Invalid credentials.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        req.getSession().setAttribute("username", user.getUsername());
        req.getSession().setAttribute("isAdmin", user.getUserRole() == UserRole.ADMIN);

        resp.sendRedirect("/");
    }
}
