package com.unicornstore.models;

import com.unicorn.store.enums.ProductCategory;
import com.unicorn.store.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(ProductCategory.ELECTRONICS);
        assertNotNull(product);
    }

    @Test
    public void testGetElectronicsCategories() {
        assertEquals(ProductCategory.ELECTRONICS, product.getProductCategory());
    }
}