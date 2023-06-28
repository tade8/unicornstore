package com.unicorn.store.repository;

import com.unicorn.store.enums.ProductCategory;
import com.unicorn.store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByProductCategory(ProductCategory productCategory);
    Optional<Product> findProductById(String id);

}
