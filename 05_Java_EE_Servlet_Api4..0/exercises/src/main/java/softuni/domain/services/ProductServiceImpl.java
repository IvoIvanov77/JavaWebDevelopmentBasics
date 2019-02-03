package softuni.domain.services;

import softuni.domain.model.Product;
import softuni.domain.repositories.ProductRepository;

import javax.inject.Inject;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(Product product) {
        this.productRepository.saveProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductByName(String name) {
        return this.productRepository.findByName(name);
    }
}
