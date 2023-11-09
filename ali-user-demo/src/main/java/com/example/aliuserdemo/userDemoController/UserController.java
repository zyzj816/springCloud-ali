package com.example.aliuserdemo.userDemoController;

import com.example.aliuserdemo.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public String getOrder(){
        return userOrderService.getDemoOrder("123");
    }

    @GetMapping("getDemoOrder")
    public String getDemoOrder(String orderId){
        System.out.println(orderId);
        return "order123456";
    }
}
