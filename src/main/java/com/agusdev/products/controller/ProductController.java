package com.agusdev.products.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.agusdev.products.model.ProductDto;
import com.agusdev.products.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
