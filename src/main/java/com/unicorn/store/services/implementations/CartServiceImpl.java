package com.unicorn.store.services.implementations;

import com.unicorn.store.exceptions.GenericException;
import com.unicorn.store.models.*;
import com.unicorn.store.repository.*;
import com.unicorn.store.services.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public String addProductToShoppingCart(String id) {
        Cart cart = new Cart();
        Product product = getProductById(id);
        cart.getProductList().add(product);
        product.setCart(cart);
        productRepository.save(product);
        cartRepository.save(cart);
        return String.format("%s added to cart", product.getProductName());
    }

    public Product getProductById(String id) {
        return productRepository.findProductById(id).
                orElseThrow(()-> new GenericException("This product does not exist"));
    }

    @Override
    @Transactional
    public String removeProductFromShoppingCart(String id, String productId) {
        Cart cart = getCartById(id);
        Product product = getProductById(productId);
        cart.getProductList().remove(product);
        cartRepository.save(cart);
        return "Product removed from cart";
    }

    private Cart getCartById(String id) {
        return cartRepository.findCartById(id).
                orElseThrow(()-> new GenericException("This cart was not found"));
    }
}
