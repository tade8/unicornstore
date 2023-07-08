package com.unicorn.store.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unicorn.store.requests.OrderRequest;
import com.unicorn.store.response.OrderResponse;
import com.unicorn.store.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("unicorn/store/order/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    public OrderResponse makePayments(@RequestBody OrderRequest orderRequest) throws JsonProcessingException {
        return orderService.makePayments(orderRequest);
    }
}
