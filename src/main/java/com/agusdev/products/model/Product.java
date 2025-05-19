package com.agusdev.products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
      private Long idProduct;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Column(name = "description")
    private String description;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que 0")
    @Column(name = "price")
    private Double price;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name = "stock")
    private Integer stock;

    @NotBlank(message = "La categoría no puede estar vacía")
    @Column(name = "category")
    private String category;

    @Column(name = "image_url")
    private String imageUrl;

    public Product() {
    }

    public Product(Long idProduct, String name, String description, Double price,
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
}
