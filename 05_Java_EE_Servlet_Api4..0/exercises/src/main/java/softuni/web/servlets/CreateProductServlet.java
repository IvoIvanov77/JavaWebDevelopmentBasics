package softuni.web.servlets;

import softuni.domain.enums.ProductType;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet("/products/create")
public class CreateProductServlet extends HttpServlet {
    private final ProductService productService;

    private final Reader htmlReader;

    @Inject
    public CreateProductServlet(ProductService productService, Reader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    private String renderProductTypes() {
        List<String> types = Arrays.stream(ProductType.values())
                .map(ProductType::toString)
                .collect(Collectors.toList());
        StringBuilder typeList = new StringBuilder();
        for (String type : types) {
            typeList.append(
                    String.format(
                            "<option value='%s'>%s</option>",
                            type, type)
            );
        }

        return typeList.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlContent = this.htmlReader.read(HTMLPaths.CREATE_PRODUCT_HTML);
        resp.getWriter()
                .println(String.format(htmlContent, this.renderProductTypes()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        p.setName(req.getParameter("name"));
        p.setDescription(req.getParameter("description"));
        p.setProductType(ProductType.valueOf(req.getParameter("type").toUpperCase()));
        this.productService.createProduct(p);
        resp.sendRedirect("/");
    }
}