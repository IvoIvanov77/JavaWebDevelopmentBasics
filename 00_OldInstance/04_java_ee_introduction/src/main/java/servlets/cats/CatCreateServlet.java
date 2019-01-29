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
        PrintWriter out = resp.getWriter();
        CatRepositoryInitializer.initialize(this.getServletContext());
        StringBuilder htmlContent = new StringBuilder("<h1>Create a Cat!</h1>");
        htmlContent
                .append("<form action=\"/cats/create\" method=\"post\">")
                .append("Name: <input type=\"text\" name=\"name\"/>")
                .append("<br/>")
                .append("Breed: <input type=\"text\" name=\"breed\"/>")
                .append("<br/>")
                .append("Color: <input type=\"text\" name=\"color\"/>")
                .append("<br/>")
                .append("Number of legs: <input type=\"text\" name=\"legs\"/>")
                .append("<br/>")
                .append("<input type=\"submit\" value=\"Create Cat\"/>")
                .append("</form>")
                .append("<p><a href=\"/\">Back To Home</a></p>");

        out.print(htmlContent.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String breed = req.getParameter("breed");
        String color = req.getParameter("color");
        int numberOfLegs = Integer.parseInt(req.getParameter("legs"));

        CatRepository catRepository = (CatRepository)this.getServletContext().getAttribute("allCats");
        catRepository.add(new Cat(name, breed, color, numberOfLegs));

        resp.sendRedirect(String.format("/cats/profile?name=%s", name));
    }
}
