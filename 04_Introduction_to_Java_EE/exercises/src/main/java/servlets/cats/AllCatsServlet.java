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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        CatRepositoryInitializer.initialize(this.getServletContext());

        CatRepository catRepository = (CatRepository)this.getServletContext()
                .getAttribute("allCats");

        StringBuilder htmlContent = new StringBuilder("<h1>All Cats!</h1><hr>");

        Collection<Cat> allCats = catRepository.getAll();

        if(allCats.isEmpty()){
            htmlContent.append("<h3>There are no cats. <a href=\"/cats/create\">Create Some!</a></h3>");
        }else{
            for (Cat cat : allCats) {
                htmlContent.append(String.format("<p><a href=\"/cats/profile?name=%s\">%s</a></p>",
                        cat.getName(), cat.getName()));
            }
        }

        htmlContent.append("<p><a href=\"/\">Back To Home</a></p>");

        out.print(htmlContent.toString());


    }
}
