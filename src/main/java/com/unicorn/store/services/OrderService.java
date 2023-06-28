package com.unicorn.store.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unicorn.store.requests.OrderRequest;
import com.unicorn.store.response.OrderResponse;

public interface OrderService {
    OrderResponse makePayments(OrderRequest orderRequest) throws JsonProcessingException;
}
