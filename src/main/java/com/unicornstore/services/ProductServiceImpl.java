package com.unicornstore.services;

import com.unicornstore.enums.ProductCategory;
import com.unicornstore.models.Product;
import com.unicornstore.models.ProductRequest;
import com.unicornstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public String addProduct(ProductRequest productRequest) {
        Product product = new Product(
                productRequest.getProductName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getProductCategory()
        );
        productRepository.save(product);
        return "Product added";
    }

    @Override
    public List<Product> viewAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> viewProductsByCategory(ProductCategory productCategory) {
        return productRepository.findByCategory(productCategory);
    }
}
