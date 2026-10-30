package Practice.Service;
import Practice.Model.Product;
import Practice.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Product createProduct(Product product) {

        if (product == null) {
            throw new RuntimeException("Product cannot be null");
        }

        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new RuntimeException("Product name is required");
        }

        if (product.getPrice() <= 0) {
            throw new RuntimeException("Price must be greater than 0");
        }

        return repository.save(product);
    }

    // READ ALL
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // READ BY ID
    public Product getProductById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // UPDATE
    public Product updateProduct(String id, Product product) {

        Product existing = getProductById(id);

        existing.setProductName(product.getProductName());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());
        existing.setDescription(product.getDescription());
        existing.setCategory(product.getCategory());

        return repository.save(existing);
    }

    // DELETE
    public void deleteProduct(String id) {
        Product existing = getProductById(id);
        repository.delete(existing);
    }
}