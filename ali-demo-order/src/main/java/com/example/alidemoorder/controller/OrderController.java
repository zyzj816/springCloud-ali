package com.example.alidemoorder.controller;

import com.example.alidemoorder.service.OrderTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderTblService orderTblService;

    @GetMapping("getDemoOrder")
    public String getDemoOrder(String orderId, String name, ServerHttpRequest token){
        System.out.println(orderId+"-"+name+"-"+token.getQueryParams().getFirst("token"));
        return "order123456";
    }

    /**
     * 用户下单接口
     * @param
     * @param
     * @return
     */
    @PostMapping("createOrder")
    public String createOrder(){
        Long userId=1l;String commodityCode="S123434455666777";
        return orderTblService.createOrder(userId,commodityCode);
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
