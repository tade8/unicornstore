package com.unicorn.store.requests;

import com.unicorn.store.enums.ProductCategory;
import com.unicorn.store.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest implements Serializable {
    private String id;
    private String productName;
    private String description;
    private String price;
    private ProductCategory productCategory;
    private UserRole userRole;

    public ProductRequest(String productName, String description, String price, ProductCategory productCategory, UserRole userRole) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.productCategory = productCategory;
        this.userRole = userRole;
    }
}
