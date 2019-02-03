package softuni.domain.services;

import softuni.domain.model.Product;

import java.util.List;

public interface ProductService {

    void createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductByName(String name);
}
