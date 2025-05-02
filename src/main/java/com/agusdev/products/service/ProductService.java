package com.agusdev.products.service;

import com.agusdev.products.model.Product;
import com.agusdev.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @Override
    public void saveProduct(Product product){
        productRepository.save(product);
    }
    @Override
    public boolean updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            // Actualiza los campos del producto existente
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setStock(product.getStock());
            updatedProduct.setCategory(product.getCategory());
            updatedProduct.setImageUrl(product.getImageUrl());
            updatedProduct.setCreatedDate(product.getCreatedDate());

            // Guarda el producto actualizado
            productRepository.save(updatedProduct);
            return true;
        } else {
            return false; // Si el producto no existe, retorna false
        }
    }
    @Override
    public boolean deleteProduct(Long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public Product getProductById(Long idProduct){
        return productRepository.findById(idProduct).orElse(null);
    }





}
