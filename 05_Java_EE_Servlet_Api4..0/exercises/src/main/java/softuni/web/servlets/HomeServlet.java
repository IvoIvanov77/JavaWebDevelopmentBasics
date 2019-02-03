package softuni.web.servlets;

import softuni.domain.model.Product;
import softuni.domain.services.ProductService;
import softuni.web.constants.HTMLPaths;
import softuni.web.io.Reader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private final ProductService productService;

    private final Reader htmlReader;

    @Inject
    public HomeServlet(ProductService productService, Reader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    private String renderListOfProducts(List<Product> products){
        StringBuilder unorderedList = new StringBuilder();
        for (Product product : products) {
            unorderedList.append(
                    String.format(
                            "<li><a href = '/products/details?name=%s'>%s</a></li>",
                            product.getName(), product.getName())
            );
        }

        return unorderedList.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlContent = this.htmlReader.read(HTMLPaths.INDEX_HTML);
        List<Product> allProducts = this.productService.getAllProducts();
        resp.getWriter().println(
                String.format(htmlContent, this.renderListOfProducts(allProducts))
        );
    }
}
