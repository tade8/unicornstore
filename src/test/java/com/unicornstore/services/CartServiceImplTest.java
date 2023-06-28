package com.unicornstore.services;

import com.unicorn.store.enums.ProductCategory;
import com.unicorn.store.models.Cart;
import com.unicorn.store.models.Product;
import com.unicorn.store.requests.ProductRequest;
import com.unicorn.store.repository.CartRepository;
import com.unicorn.store.repository.ProductRepository;
import com.unicorn.store.services.implementations.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
    @InjectMocks
    private CartServiceImpl cartService;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    private Product product;
    private ProductRequest productRequest;
    private Cart cart;

    @BeforeEach
    public void setUp() {
        productRequest = new ProductRequest();
        product = new Product(
                "MacBook",
                "15-inch MacBook Pro",
                new BigDecimal(2_000_000),
                ProductCategory.ELECTRONICS
        );
        cart = new Cart();
    }

    @Test
    @DisplayName("Add Product To ShoppingCart Gets Saved To ShoppingCart And Returns Message")
    void addProductTo_ShoppingCart() {
        when(productRepository.findProductById(product.getId())).thenReturn(Optional.ofNullable(product));
        when(cartRepository.save(any())).then(returnsFirstArg());
        String message = cartService.addProductToShoppingCart(product.getId());

        verify(cartRepository).save(any());
        assertEquals("MacBook added to cart", message);
        assertNotNull(cart.getProductList());
    }

    @Test
    public void removeProductFromShoppingCart() {
        addProductTo_ShoppingCart();
        assertNotNull(cart.getProductList());

        when(cartRepository.findCartById(cart.getId())).thenReturn(Optional.of(cart));
        when(productRepository.findProductById(product.getId())).thenReturn(Optional.ofNullable(product));
        cartService.removeProductFromShoppingCart(cart.getId(), productRequest.getId());

        assertEquals(0, cart.getProductList().size());
        assertTrue(cart.getProductList().isEmpty());
    }
}
