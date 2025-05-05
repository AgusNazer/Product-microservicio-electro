package com.agusdev.products.model;

import java.time.LocalDate;

public class ProductDto {

    private Long idProduct;
    private String name;
    private String description;
    private Double price;
    private LocalDate createdDate;
    private Integer stock;
    private String category;
    private String imageUrl;

    public ProductDto() {
    }

    public ProductDto(Long idProduct, String name, String description, Double price,
                      LocalDate createdDate, Integer stock, String category, String imageUrl) {
        this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdDate = createdDate;
        this.stock = stock;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    // Getters y Setters

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
