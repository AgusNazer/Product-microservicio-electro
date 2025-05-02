package com.agusdev.products.controller;

import com.agusdev.products.model.Product;
import com.agusdev.products.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping
    public String saveProduct(@RequestBody @Valid Product product) {
        productService.saveProduct(product);
        return "Product created successfully";
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{idProduct}")
    public Product getProductById(@PathVariable Long idProduct) {
        return productService.getProductById(idProduct);
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) {
        boolean updated = productService.updateProduct(id, product);
        return updated ? "Product updated successfully" : "Product not found";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted ? "Product deleted successfully" : "Product not found";
    }
}

