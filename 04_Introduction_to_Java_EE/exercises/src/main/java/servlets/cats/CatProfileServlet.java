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
        PrintWriter out = resp.getWriter();
        CatRepositoryInitializer.initialize(this.getServletContext());

        String name = req.getParameter("name");

        CatRepository catRepository = (CatRepository)this.getServletContext()
                .getAttribute("allCats");

        Cat cat = catRepository.get(name);

        StringBuilder htmlContent = new StringBuilder();

        if(cat == null){
            out.print(String.format("<h1>Cat, with name- %s was not found.</h1>", name));

        }else{
            htmlContent
                    .append(String.format("<h1>Cat - %s</h1><hr>", cat.getName()))
                    .append(String.format("<h3>Breed: %s</h3>", cat.getBreed()))
                    .append(String.format("<h3>Color: %s</h3>", cat.getColor()))
                    .append(String.format("<h3>Number of legs: %s</h3>", cat.getNumberOfLegs()))
                    .append("<hr>");
        }

      htmlContent
                .append("<p><a href=\"/cats/all\">Back</a></p>");


        out.print(htmlContent.toString());


    }


}
