package main.services;

import main.entities.Product;
import main.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Page<Product> getProducts(Pageable paging) {
        return this.productRepository.findAll(paging);
    }

    public Product getProductById(int id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        return this.productRepository.findByName(name);
    }

    public String deleteProduct(int id) {
        this.productRepository.deleteById(id);
        return "Product deleted: " + id;
    }

    public Product updateProduct(Product product) {
        Product existingProduct = this.productRepository.findById(product.getId()).orElse(null);
        if (existingProduct == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Product with id %d not found.", product.getId())
            );
        }
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return this.productRepository.save(existingProduct);
    }
}
