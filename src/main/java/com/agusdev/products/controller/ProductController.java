package com.agusdev.products.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.agusdev.products.model.ProductDto;
import com.agusdev.products.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://product-microservicio-electro.onrender.com"
})

@RestController
@RequestMapping("/products")
//Swagger
@Tag(name = "Product", description = "API for managing products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping
    public String saveProduct(@RequestBody @Valid ProductDto productDto) {
        productService.saveProduct(productDto); // Pasar el DTO al servicio
        return "Product created successfully";
    }
    @Operation(summary = "Create multiple products in a single request",
            description = "Creates multiple products from a list and returns the IDs of created products")
    @PostMapping("/bulk")
    public String saveProducts(@RequestBody List<ProductDto> productDtos) {
        productService.saveProducts(productDtos);
        return "Products created successfully";
    }



    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts(); // Obtener la lista de ProductDto
    }

    @GetMapping("/{idProduct}")
    public ProductDto getProductById(@PathVariable Long idProduct) {
        return productService.getProductById(idProduct); // Obtener ProductDto por ID
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
        boolean updated = productService.updateProduct(id, productDto); // Pasar el DTO para actualizar
        return updated ? "Product updated successfully" : "Product not found";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id); // Eliminar producto
        return deleted ? "Product deleted successfully" : "Product not found";
    }
    @PostMapping("/decreaseStock")
    public String decreaseStock(
            @RequestParam Long productId,
            @RequestParam int quantity) {
        boolean success = productService.decreaseStock(productId, quantity);
        return success ? "Stock decreased successfully" : "Failed to decrease stock";
    }
    @PutMapping("/bulk-update")
    public String updateMultipleProducts(@RequestBody List<ProductDto> products) {
        int updatedCount = productService.updateMultipleProducts(products);
        return updatedCount + " products updated successfully.";
    }



}
