package com.agusdev.products.service;

import com.agusdev.products.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getAllProducts();

    public void saveProduct(Product product);

    public boolean updateProduct(Long id, Product product);

    public boolean deleteProduct(Long id);

    public Product getProductById(Long id);
}
