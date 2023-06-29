package com.unicornstore.services;

import com.unicorn.store.models.Product;
import com.unicorn.store.repository.ProductRepository;
import com.unicorn.store.services.implementations.RatingServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTest {
    @InjectMocks
    private RatingServiceImpl ratingService;
    @Mock
    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
    }

    @Test
    public void rateProduct() {
        when(productRepository.findProductById(product.getId())).thenReturn(Optional.of(product));
        ratingService.rateProduct(product.getId());
        assertEquals(1, product.getRating());

        ratingService.rateProduct(product.getId());
        assertEquals(2, product.getRating());
    }

    @Test
    public void removeRating() {
        when(productRepository.findProductById(product.getId())).thenReturn(Optional.of(product));
        ratingService.rateProduct(product.getId());
        assertEquals(1, product.getRating());

        ratingService.removeProductRating(product.getId());
        assertEquals(0, product.getRating());
    }

    @Test
    public void canOnlyRemoveRatingFromProductThatHasRating() {
        when(productRepository.findProductById(product.getId())).thenReturn(Optional.of(product));
        assertEquals("Product has no rating", ratingService.removeProductRating(product.getId()));
    }
}
