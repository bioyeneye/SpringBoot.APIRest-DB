package main.controllers;

import main.entities.Product;
import main.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired private ProductService productService;

    @PostMapping("")
    public Product addProduct(@RequestBody Product product) {
        return this.productService.saveProduct(product);
    }

    @PostMapping("/addproducts")
    public List<Product> addProduct(@RequestBody List<Product> products) {
        return this.productService.saveProducts(products);
    }

    @RequestMapping("")
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(required = true) int id) {
        return this.productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product) {
        return this.productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public String updateProduct(@PathVariable int id) {
        return this.productService.deleteProduct(id);
    }
}
