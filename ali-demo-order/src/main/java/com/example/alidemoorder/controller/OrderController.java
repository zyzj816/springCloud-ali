package com.example.alidemoorder.controller;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("getDemoOrder")
    public String getDemoOrder(String orderId, String name, ServerHttpRequest token){
        System.out.println(orderId+"-"+name+"-"+token.getQueryParams().getFirst("token"));
        return "order123456";
    }

    /**
     * 测试负载均衡
     * @return
     */
    @GetMapping("lb")
    public String lb(){
        System.out.println("test lb");
        return "lb";
    }


}
