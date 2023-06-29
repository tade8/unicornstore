package com.unicorn.store.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unicorn.store.requests.OrderRequest;
import com.unicorn.store.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("unicorn/store/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @MutationMapping
    public String makePayments(@Argument OrderRequest orderRequest) throws JsonProcessingException {
        return orderService.makePayments(orderRequest).getPaymentUrl();
    }
}
