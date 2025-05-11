package com.agusdev.products.service;

import com.agusdev.products.model.ProductDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

public interface IProductService {

    List<ProductDto> getAllProducts();

    void saveProduct(ProductDto productDto);

    boolean updateProduct(Long id, ProductDto productDto);

    boolean deleteProduct(Long id);

    ProductDto getProductById(Long id);

    boolean decreaseStock(Long productID, int quantity);


    void saveProducts(List<ProductDto> productDtos);

    int updateMultipleProducts(List<ProductDto> productDtos);


}
