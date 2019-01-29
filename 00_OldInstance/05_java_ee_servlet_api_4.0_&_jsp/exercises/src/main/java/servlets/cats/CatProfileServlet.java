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

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//        CatRepositoryInitializer.initialize(this.getServletContext());
        User user = UserAuthentication.getCurrentUser(req, this.getServletContext());
        if(user == null){
            resp.sendRedirect("/users/login");
            return;
        }

        String name = req.getParameter("name");

        CatRepository catRepository = (CatRepository)this.getServletContext()
                .getAttribute("cats");

        Cat cat = catRepository.get(name);
        cat.incrementViews();

        req.setAttribute("cat", cat);

        req.getRequestDispatcher("profile.jsp").forward(req, resp);


    }


}
