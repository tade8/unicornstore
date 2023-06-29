package com.unicornstore.services;

import com.unicorn.store.enums.ProductCategory;
import com.unicorn.store.models.*;
import com.unicorn.store.requests.ProductRequest;
import com.unicorn.store.repository.*;
import com.unicorn.store.services.implementations.CartServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        cart = new Cart();
        product = new Product(
                "MacBook",
                "15-inch MacBook Pro",
                new BigDecimal(2_000_000),
                ProductCategory.ELECTRONICS
        );
    }

    @Test
    @DisplayName("Add Product To ShoppingCart Gets Saved To ShoppingCart And Returns Message")
    void addProductTo_ShoppingCart() {
        when(productRepository.findProductById(product.getId())).thenReturn(Optional.of(product));
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
        cartService.removeProductFromShoppingCart(cart.getId(), productRequest.getId());

        assertEquals(0, cart.getProductList().size());
        assertTrue(cart.getProductList().isEmpty());
    }
}
