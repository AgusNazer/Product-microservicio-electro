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
    public ProductDto getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(this::convertToDTO)
                .orElse(null);
    }
    @Override
    public boolean decreaseStock(Long productId, int quantity) {
        ProductDto product = getProductById(productId);
        if (product == null || product.getStock() < quantity) {
            return false; // No hay suficiente stock o producto no encontrado
        }
        product.setStock(product.getStock() - quantity);
        updateProduct(productId, product); // reutiliza tu método de update
        return true;
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
    @Override
    public void saveProducts(List<ProductDto> productDtos) {
        List<Product> products = productDtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());

        productRepository.saveAll(products);
        System.out.println("Productos guardados: " + products.size());

    }

    public int updateMultipleProducts(List<ProductDto> products) {
        int count = 0;
        for (ProductDto dto : products) {
            Optional<Product> existingProduct = productRepository.findById(dto.getIdProduct());
            if (existingProduct.isPresent()) {
                Product updatedProduct = existingProduct.get();
                updatedProduct.setName(dto.getName());
                updatedProduct.setDescription(dto.getDescription());
                updatedProduct.setPrice(dto.getPrice());
                updatedProduct.setStock(dto.getStock());
                updatedProduct.setCategory(dto.getCategory());
                updatedProduct.setImageUrl(dto.getImageUrl());
                updatedProduct.setCreatedDate(dto.getCreatedDate());
                productRepository.save(updatedProduct);
                count++;
            }
        }
        return count;
    }

}
