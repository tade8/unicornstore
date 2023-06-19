package com.unicornstore.services;

import com.unicornstore.enums.ProductCategory;
import com.unicornstore.enums.UserRole;
import com.unicornstore.models.ProductRequest;
import com.unicornstore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

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
                ProductCategory.ELECTRONICS,
                UserRole.ADMIN
        );
    }

    @Test
    public void testProductExists() {
        assertNotNull(productRequest);
    }

    @Test
    void addProductToProductsListGetsSaved() {
        when(productRepository.save(any())).then(returnsFirstArg());
        productService.addProductToProductsList(productRequest);
        verify(productRepository).save(any());
    }

    @Test
    void onlyAdmin_CanAddProduct_ToProductsList() {
        when(productRepository.save(any())).then(returnsFirstArg());
        productService.addProductToProductsList(productRequest);
        assertEquals(UserRole.ADMIN, productRequest.getUserRole());
        assertNotEquals(UserRole.CUSTOMER, productRequest.getUserRole());
    }

    @Test
    public void viewAllProducts() {
        productService.viewAllProducts();
        verify(productRepository).findAll();
    }

    @Test
    public void viewProductBy_CategoryReturns_CategoryObject() {
        productService.viewProductsByCategory(ProductCategory.BEVERAGES);
        verify(productRepository).findProductByCategory(ProductCategory.BEVERAGES);
    }
}
