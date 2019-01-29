package servlets.cats;

import data.Cat;
import data.User;
import enums.UserRole;
import repository.CatRepository;
import util.CatRepositoryInitializer;
import util.UserAuthentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//        CatRepositoryInitializer.initialize(this.getServletContext());
        User user = UserAuthentication.getCurrentUser(req, this.getServletContext());
        if(user == null){
            resp.sendRedirect("/users/login");
            return;
        }
        if(!user.getUserRole().equals(UserRole.ADMIN)){
            resp.sendRedirect("/");
            return;
        }
        req.getRequestDispatcher("create.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = UserAuthentication.getCurrentUser(req, this.getServletContext());
        if(user == null){
            resp.sendRedirect("/users/login");
            return;
        }
        if(!user.getUserRole().equals(UserRole.ADMIN)){
            resp.sendRedirect("/");
            return;
        }
        String name = req.getParameter("name");
        String breed = req.getParameter("breed");
        String color = req.getParameter("color");
        int numberOfLegs = Integer.parseInt(req.getParameter("legs"));

        CatRepository catRepository = (CatRepository)this.getServletContext().getAttribute("cats");
        catRepository.add(new Cat(name, breed, color, numberOfLegs));

        resp.sendRedirect(String.format("/cats/profile?name=%s", name));
    }
}
