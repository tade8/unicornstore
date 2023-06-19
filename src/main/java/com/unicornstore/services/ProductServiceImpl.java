package com.unicornstore.services;

import com.unicornstore.enums.ProductCategory;
import com.unicornstore.enums.UserRole;
import com.unicornstore.models.Product;
import com.unicornstore.models.ProductRequest;
import com.unicornstore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String addProductToProductsList(ProductRequest productRequest) {
        Product product = new Product(
                productRequest.getProductName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getProductCategory(),
                UserRole.ADMIN
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
        return productRepository.findProductByCategory(productCategory);
    }
}
