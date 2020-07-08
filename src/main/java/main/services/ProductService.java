package main.services;

import main.entities.Product;
import main.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> saveProducts(List<Product> products){
        return this.productRepository.saveAll(products);
    }

    public List<Product> getProducts() {
        return this.productRepository.findAll();
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
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return this.productRepository.save(existingProduct);
    }
}
