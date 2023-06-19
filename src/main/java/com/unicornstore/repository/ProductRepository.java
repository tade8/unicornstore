package com.unicornstore.repository;

import com.unicornstore.enums.ProductCategory;
import com.unicornstore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findProductByCategory(ProductCategory productCategory);
}
