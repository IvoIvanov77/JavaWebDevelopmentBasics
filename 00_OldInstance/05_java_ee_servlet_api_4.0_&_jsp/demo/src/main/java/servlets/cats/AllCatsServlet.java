package servlets.cats;

import data.Cat;
import repository.CatRepository;
import util.CatRepositoryInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CatRepositoryInitializer.initialize(this.getServletContext());

        CatRepository catRepository = (CatRepository)this.getServletContext()
                .getAttribute("allCats");

        Collection<Cat> allCats = catRepository.getAll();

        req.setAttribute("allCats", allCats);

        req.getRequestDispatcher("allCats.jsp").forward(req, resp);
    }
}
