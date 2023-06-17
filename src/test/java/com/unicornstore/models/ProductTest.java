package com.unicornstore.models;

import com.unicornstore.enums.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(ProductCategory.ELECTRONICS);
    }

    @Test
    public void testGetElectronicsCategories() {
        assertEquals(ProductCategory.ELECTRONICS, product.getProductCategory());
    }
}