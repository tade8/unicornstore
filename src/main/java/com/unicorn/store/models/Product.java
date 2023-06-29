package com.unicorn.store.models;

import com.unicorn.store.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    private String productName;
    @NotBlank
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private int rating;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public Product(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Product(String productName, String description, BigDecimal price, ProductCategory productCategory, UserRole userRole) {
        this(productCategory);
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.userRole = userRole;
    }

    public Product(String productName, String description, BigDecimal price, ProductCategory productCategory) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.productCategory = productCategory;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }
}
