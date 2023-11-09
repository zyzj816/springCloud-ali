package com.example.alidemoorder.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("getDemoOrder")
    public String getDemoOrder(String orderId){
        System.out.println(orderId);
        return "order123456";
    }
}
