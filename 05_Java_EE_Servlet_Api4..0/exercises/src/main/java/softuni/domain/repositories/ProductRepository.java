package softuni.domain.repositories;

import softuni.domain.model.Product;
import javax.persistence.NoResultException;
import java.util.List;


public class ProductRepository extends BaseRepository {
    public ProductRepository() {

    }

    public Product findByName(String name) {
        Product result = (Product) executeAction(repositoryActionResult -> {
            Product productFromDb = null;
            try{
                productFromDb = (Product) this.entityManager.createQuery(
                        "select p from Product p where p.name = :name ")
                        .setParameter("name", name)
                        .getSingleResult();
            }catch (NoResultException e){
                ;
            }
            repositoryActionResult.setResult(productFromDb);

        }).getResult();

        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        List<Product> result = (List<Product>) executeAction(repositoryActionResult -> {
            repositoryActionResult.setResult(
                    this.entityManager.createQuery(
                            "select p from Product p")
                            .getResultList());
        }).getResult();

        return result;
    }

    public void saveProduct(Product product) {
        executeAction(repositoryActionResult -> this.entityManager.persist(product));
    }





}
