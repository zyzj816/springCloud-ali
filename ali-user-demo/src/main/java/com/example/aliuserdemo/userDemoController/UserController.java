package com.example.aliuserdemo.userDemoController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.aliuserdemo.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

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
    public String getOrder(String userId, String name, ServerHttpRequest request){
        return userOrderService.getDemoOrder(userId,name,request);
    }

    @GetMapping("getDemoOrder")
    public String getDemoOrder(String orderId){
        System.out.println(orderId);
        return "order123456";
    }

}
