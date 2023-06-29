package com.unicorn.store.controller;

import com.unicorn.store.enums.ProductCategory;
import com.unicorn.store.models.Product;
import com.unicorn.store.requests.ProductRequest;
import com.unicorn.store.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("unicorn/store/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @MutationMapping
    public String addProductsToList(@Argument ProductRequest productRequest) {
         return productService.addProductToProductsList(productRequest);
    }

    @QueryMapping
    public List<Product> viewAllProducts() {
        return productService.viewAllProducts();
    }

    @QueryMapping
    public List<Product> viewProductsByCategory(@Argument ProductCategory productCategory) {
        return productService.viewProductsByCategory(productCategory);
    }

    @QueryMapping
    public String removeProductFromProductsList(@Argument String id) {
        return productService.removeProductFromProductsList(id);
    }
}
