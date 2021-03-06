package main.controllers;

import io.micrometer.core.annotation.Timed;
import main.entities.Product;
import main.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/products", produces = "application/json")
public class ProductController {

    @Autowired private ProductService productService;

    @PostMapping("")
    public Product Post(@RequestBody Product product) {
        return this.productService.saveProduct(product);
    }

    @Timed
    @Transactional(readOnly = true)
    @RequestMapping(value = "",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Product> Get(
            @PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = "id") Pageable page) {
        return this.productService.getProducts(page);
    }

    @GetMapping("/{id}")
    public Product GetById(@PathVariable(required = true) int id) {
        Product existingProduct = this.productService.getProductById(id);
        if (existingProduct == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Product with id %d not found.", id)
            );
        }

        return existingProduct;
    }

    @PutMapping("/{id}")
    public Product Update(@RequestBody Product product, @PathVariable(required = true) int id) {

        return this.productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public String Delete(@PathVariable int id) {
        return this.productService.deleteProduct(id);
    }
}
