package com.agusdev.products.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long idProduct;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private String imageUrl;
}
