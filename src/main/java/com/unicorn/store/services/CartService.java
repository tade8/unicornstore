package com.unicorn.store.services;

public interface CartService {
    String addProductToShoppingCart(String id);
    String removeProductFromShoppingCart(String id, String productId);
}
