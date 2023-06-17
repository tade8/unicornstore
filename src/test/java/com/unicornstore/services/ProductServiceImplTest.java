package com.unicornstore.services;

import com.unicornstore.enums.ProductCategory;
import com.unicornstore.models.Product;
import com.unicornstore.models.ProductRequest;
import com.unicornstore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    private ProductRequest productRequest;

    @BeforeEach
    void setUp() {
        productRequest = new ProductRequest(
                "MacBook",
                "15-inch MacBook Pro",
                new BigDecimal(2_000_000),
                ProductCategory.ELECTRONICS
        );
    }

    @Test
    public void testProductExists() {
        assertNotNull(productRequest);
    }

    @Test
    void addProduct() {
        when(productRepository.save(any())).then(returnsFirstArg());
        productService.addProduct(productRequest);
        assertEquals(ProductCategory.ELECTRONICS, productRequest.getProductCategory());
        verify(productRepository).save(any());
    }

    @Test
    public void viewAllProducts() {
        productService.viewAllProducts();
        verify(productRepository).findAll();
    }

    @Test
    public void viewProductByCategory() {
        Product product = new Product("Coffee", ProductCategory.BEVERAGES);
        Product product2 = new Product("MacBook", ProductCategory.ELECTRONICS);

        when(productRepository.findByCategory(ProductCategory.BEVERAGES)).thenReturn(List.of(product));
        productService.viewProductsByCategory(ProductCategory.BEVERAGES);

        assertEquals(productRepository.findByCategory(ProductCategory.BEVERAGES),
                List.of(product));
    }
}
