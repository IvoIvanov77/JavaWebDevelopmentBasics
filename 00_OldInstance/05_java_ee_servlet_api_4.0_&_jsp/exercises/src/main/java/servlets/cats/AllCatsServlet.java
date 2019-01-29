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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        CatRepositoryInitializer.initialize(this.getServletContext());
        User user = UserAuthentication.getCurrentUser(req, this.getServletContext());
        if(user == null){
            resp.sendRedirect("/users/login");
            return;
        }

        CatRepository catRepository = (CatRepository)this.getServletContext()
                .getAttribute("cats");

        Collection<Cat> allCats = catRepository.getAll();
        List<Cat> sortedCats = new ArrayList<>(allCats);
        sortedCats.sort((cat1, cat2) -> cat2.getViews() - cat1.getViews());

        req.setAttribute("allCats", sortedCats);

        req.getRequestDispatcher("allCats.jsp").forward(req, resp);
    }
}
