package com.example.aliuserdemo.userDemoController;

import com.example.aliuserdemo.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserOrderService userOrderService;


    @Value("${demo.userId}")
    private String userId;

    @GetMapping("getUserId")
    public String getUsrIdTest(){
        System.out.println(userId);
        return userId;
    }

    @GetMapping("getOrder")
    public String getOrder(String userId, String name, ServerHttpRequest request){
        return userOrderService.getDemoOrder(userId,name,request);
    }

    @GetMapping("getDemoOrder")
    public String getDemoOrder(String orderId){
        System.out.println(orderId);
        return "order123456";
    }



}
