package com.agusdev.products.service;

import com.agusdev.products.model.Product;
import com.agusdev.products.model.ProductDto;
import com.agusdev.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        Product product = convertToEntity(productDto);
        productRepository.save(product);
    }

    @Override
    public boolean updateProduct(Long id, ProductDto productDto) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            // Actualiza los campos del producto existente
            updatedProduct.setName(productDto.getName());
            updatedProduct.setDescription(productDto.getDescription());
            updatedProduct.setPrice(productDto.getPrice());
            updatedProduct.setStock(productDto.getStock());
            updatedProduct.setCategory(productDto.getCategory());
            updatedProduct.setImageUrl(productDto.getImageUrl());
            updatedProduct.setCreatedDate(productDto.getCreatedDate());

            // Guarda el producto actualizado
            productRepository.save(updatedProduct);
            return true;
        } else {
            return false; // Si el producto no existe, retorna false
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ProductDto getProductById(Long idProduct) {
        return productRepository.findById(idProduct)
                .map(this::convertToDTO)
                .orElse(null);
    }

    // Métodos para convertir entre entidades y DTOs
    private ProductDto convertToDTO(Product product) {
        return new ProductDto(
                product.getIdProduct(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedDate(),
                product.getStock(),
                product.getCategory(),
                product.getImageUrl()
        );
    }

    private Product convertToEntity(ProductDto productDto) {
        return new Product(
                productDto.getIdProduct(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getCreatedDate(),
                productDto.getStock(),
                productDto.getCategory(),
                productDto.getImageUrl()
        );
    }
}
