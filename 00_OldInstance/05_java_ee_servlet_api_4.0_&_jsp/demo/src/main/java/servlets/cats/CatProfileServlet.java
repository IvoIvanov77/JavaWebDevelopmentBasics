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

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        CatRepositoryInitializer.initialize(this.getServletContext());

        String name = req.getParameter("name");

        CatRepository catRepository = (CatRepository)this.getServletContext()
                .getAttribute("allCats");

        Cat cat = catRepository.get(name);

        req.setAttribute("cat", cat);

        req.getRequestDispatcher("profile.jsp").forward(req, resp);


    }


}
