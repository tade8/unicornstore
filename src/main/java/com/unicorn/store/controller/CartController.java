package com.unicorn.store.controller;

import com.unicorn.store.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("unicorn/store/product")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @QueryMapping
    public String addProductToShoppingCart(@Argument String id) {
        return cartService.addProductToShoppingCart(id);
    }

    @QueryMapping
    public String removeProductFromShoppingCart(@Argument String id, @Argument String productId) {
        return cartService.removeProductFromShoppingCart(id, productId);
    }
}
