package com.example.aliuserdemo.service;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;



public interface UserOrderService {
    String getDemoOrder(String orderId, String name, ServerHttpRequest request);
}
