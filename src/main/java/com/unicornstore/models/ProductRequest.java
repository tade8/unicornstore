package com.unicornstore.models;

import com.unicornstore.enums.ProductCategory;
import com.unicornstore.enums.Rating;
import com.unicornstore.enums.UserRole;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String id;
    private String productName;
    private String description;
    private BigDecimal price;
    private ProductCategory productCategory;
    private Cart cart;
    private Rating rating;
    private UserRole userRole;

    public ProductRequest() {}

    public ProductRequest(String productName, String description, BigDecimal price, ProductCategory productCategory) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.productCategory = productCategory;
    }

    public ProductRequest(String productName, ProductCategory productCategory) {
        this.productName = productName;
        this.productCategory = productCategory;
    }
}
