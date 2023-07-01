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
    private String filePath;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private int rating;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public Product(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Product(String productName, String description, BigDecimal price, ProductCategory productCategory, UserRole userRole, String filePath) {
        this(productCategory);
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.userRole = userRole;
        this.filePath = filePath;
    }

    public Product(String productName, String description, BigDecimal price, ProductCategory productCategory, UserRole userRole) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.productCategory = productCategory;
        this.userRole = userRole;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }
}
