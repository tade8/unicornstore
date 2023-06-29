package com.unicorn.store.services;

public interface RatingService {
    int rateProduct(String id);
    String removeProductRating(String id);
}
