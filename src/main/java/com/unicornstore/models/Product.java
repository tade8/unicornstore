package com.unicornstore.models;

import com.unicornstore.enums.ProductCategory;
import com.unicornstore.enums.Rating;
import com.unicornstore.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String productName;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @ManyToOne
    private Cart cart;
    @Enumerated(EnumType.STRING)
    private Rating rating;
    private UserRole userRole;

    public Product(ProductCategory productCategory) {
        this.productCategory = productCategory;
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
