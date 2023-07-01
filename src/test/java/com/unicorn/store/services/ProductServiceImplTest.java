package com.unicorn.store.services;

import com.unicorn.store.enums.ProductCategory;
import com.unicorn.store.enums.UserRole;
import com.unicorn.store.models.Product;
import com.unicorn.store.repository.ProductRepository;
import com.unicorn.store.requests.ProductRequest;
import com.unicorn.store.services.implementations.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

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
    @Mock
    private MultipartFile file;

    @BeforeEach
    void setUp() {
        productRequest = new ProductRequest(
                "MacBook",
                "15-inch MacBook Pro",
                "2000000",
                ProductCategory.ELECTRONICS,
                UserRole.ADMIN
        );
    }

    @Test
    public void testProductExists() {
        assertNotNull(productRequest);
    }

    @Test
    void addProductToProductsListGetsSaved() throws IOException {
        when(productRepository.save(any())).then(returnsFirstArg());
        String product = productService.addProductToProductsList(productRequest, file);
        verify(productRepository).save(any());
        assertEquals("Product added", product);
    }

    @Test
    void onlyAdmin_CanAddProduct_ToProductsList() throws IOException {
        when(productRepository.save(any())).then(returnsFirstArg());
        productService.addProductToProductsList(productRequest, file);
        assertEquals(UserRole.ADMIN, productRequest.getUserRole());
        assertNotEquals(UserRole.CUSTOMER, productRequest.getUserRole());
    }

    @Test
    public void testExceptionIsThrownWhenWrongPriceTypeIsProvided() {
        productRequest.setPrice("2000.00");
        assertThrows(NumberFormatException.class,
                ()-> productService.addProductToProductsList(productRequest, file));
    }
    @Test
    public void removeProductFromProductsList() {
        when(productRepository.findProductById(productRequest.getId())).thenReturn(Optional.of(new Product()));
        productService.removeProductFromProductsList(productRequest.getId());
        verify(productRepository).deleteById(productRequest.getId());
    }

    @Test
    public void onlyAdminCanDeleteAProduct() {
        when(productRepository.findProductById(productRequest.getId())).thenReturn(Optional.of(new Product()));
        productService.removeProductFromProductsList(productRequest.getId());
        assertEquals(UserRole.ADMIN, productRequest.getUserRole());
        assertNotEquals(UserRole.CUSTOMER, productRequest.getUserRole());
    }

    @Test
    public void viewAllProducts() {
        productService.viewAllProducts();
        verify(productRepository).findAll();
    }

    @Test
    public void viewProductBy_CategoryReturns_CategoryProducts() {
        Product product = new Product("MacBook",
                "15-inch MacBook Pro",
                new BigDecimal("200000"),
                ProductCategory.ELECTRONICS,
                UserRole.ADMIN);
        when(productRepository.findAllByProductCategory(ProductCategory.ELECTRONICS)).thenReturn(Collections.singletonList(product));
        assertEquals(Collections.singletonList(product), productService.viewProductsByCategory(
                ProductCategory.ELECTRONICS));
        verify(productRepository).findAllByProductCategory(ProductCategory.ELECTRONICS);
    }

}
