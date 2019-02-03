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

@WebServlet("/products/details")
public class ProductsDetailsServlet extends HttpServlet {
    private final ProductService productService;

    private final Reader htmlReader;

    @Inject
    public ProductsDetailsServlet(ProductService productService, Reader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    private String renderProductDetails(String htmlContent, Product product){
        return String.format(htmlContent,
                product.getName(),
                product.getDescription(),
                product.getProductType().toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = this.productService.getProductByName(req.getParameter("name"));
        String htmlContent = this.htmlReader.read(HTMLPaths.PRODUCT_DETAILS_HTML);
        resp.getWriter().println(this.renderProductDetails(htmlContent, product));
    }
}
