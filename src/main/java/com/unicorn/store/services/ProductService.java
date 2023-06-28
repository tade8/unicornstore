package com.unicorn.store.services;

import com.unicorn.store.enums.ProductCategory;
import com.unicorn.store.models.Product;
import com.unicorn.store.requests.ProductRequest;

import java.util.List;

public interface ProductService {
    String addProductToProductsList(ProductRequest productRequest);
    List<Product> viewAllProducts();

    List<Product> viewProductsByCategory(ProductCategory productCategory);

    String removeProductFromProductsList(String id);
}
