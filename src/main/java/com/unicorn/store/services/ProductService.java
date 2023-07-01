package com.unicorn.store.services;

import com.unicorn.store.enums.ProductCategory;
import com.unicorn.store.models.Product;
import com.unicorn.store.requests.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    String addProductToProductsList(ProductRequest productRequest, MultipartFile file) throws IOException;
    List<Product> viewAllProducts();
    List<Product> viewProductsByCategory(ProductCategory productCategory);
    String removeProductFromProductsList(String id);
}
