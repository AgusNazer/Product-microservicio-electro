package com.agusdev.products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que 0")
    private Double price;

    private LocalDate createdDate;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotBlank(message = "La categoría no puede estar vacía")
    private String category;

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
