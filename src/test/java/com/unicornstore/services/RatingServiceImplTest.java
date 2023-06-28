package com.unicornstore.services;

import com.unicorn.store.models.Product;
import com.unicorn.store.repository.ProductRepository;
import com.unicorn.store.services.implementations.RatingServiceImpl;
import org.junit.jupiter.api.Test;
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

    @Test
    public void rateProduct() {
        Product product = new Product();
        when(productRepository.findProductById(product.getId())).thenReturn(Optional.of(product));
        ratingService.rateProduct(product.getId());
        assertEquals(1, product.getRating());

        ratingService.rateProduct(product.getId());
        assertEquals(2, product.getRating());
    }
}
