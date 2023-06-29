package com.unicorn.store.controller;

import com.unicorn.store.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("unicorn/store/product")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @QueryMapping
    public int rateProduct(@Argument String id) {
        return ratingService.rateProduct(id);
    }

    @QueryMapping
    public String removeProductRating(@Argument String id) {
        return ratingService.removeProductRating(id);
    }
}
