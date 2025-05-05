package com.agusdev.products.service;

import com.agusdev.products.model.ProductDto;

import java.util.List;

public interface IProductService {

    List<ProductDto> getAllProducts();

    void saveProduct(ProductDto productDto);

    boolean updateProduct(Long id, ProductDto productDto);

    boolean deleteProduct(Long id);

    ProductDto getProductById(Long id);
}
