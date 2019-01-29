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

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        CatRepositoryInitializer.initialize(this.getServletContext());
        req.getRequestDispatcher("create.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");
        String breed = req.getParameter("breed");
        String color = req.getParameter("color");
        int numberOfLegs = Integer.parseInt(req.getParameter("legs"));

        CatRepository catRepository = (CatRepository)this.getServletContext().getAttribute("allCats");
        catRepository.add(new Cat(name, breed, color, numberOfLegs));

        resp.sendRedirect(String.format("/cats/profile?name=%s", name));
    }
}
