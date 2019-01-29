package servlets.users;

import data.User;
import enums.UserRole;
import repository.UserRepository;
import util.UserAuthentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/users/register")
public class UserRegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = UserAuthentication.getCurrentUser(req, this.getServletContext());
        if(currentUser != null){
            resp.sendRedirect("/");
            return;
        }
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = UserAuthentication.getCurrentUser(req, this.getServletContext());
        if(currentUser != null){
            resp.sendRedirect("/");
            return;
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm");

        if(username.trim().isEmpty() || password.trim().isEmpty()){
            req.setAttribute("errorMessage", "Username and passwords can not be empty.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        if(!confirmPassword.equals(password)){
            req.setAttribute("errorMessage", "Passwords do not match.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }


        UserRepository userRepository = (UserRepository) this.getServletContext()
                .getAttribute("users");
        User user = new User(username, password);
        if(userRepository.getAll().isEmpty()){
            user.setUserRole(UserRole.ADMIN);
        }
        userRepository.add(user);

        resp.sendRedirect("/users/login");
    }
}
