package com.unicornstore.services;

import com.unicornstore.enums.ProductCategory;
import com.unicornstore.models.Product;
import com.unicornstore.models.ProductRequest;

import java.util.List;

public interface ProductService {
    String addProduct(ProductRequest productRequest);
    List<Product> viewAllProducts();

    List<Product> viewProductsByCategory(ProductCategory productCategory);
}
