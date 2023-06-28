package com.unicorn.store.services.implementations;

import com.unicorn.store.repository.ProductRepository;
import com.unicorn.store.models.Product;
import com.unicorn.store.services.RatingService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    private final ProductRepository productRepository;

    public RatingServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public int rateProduct(String id) {
        Product product = productRepository.findProductById(id).
                orElseThrow(()-> new NullPointerException("This product does not exist"));
        product.setRating(product.getRating()+1);
        productRepository.save(product);
        return product.getRating();
    }
}
