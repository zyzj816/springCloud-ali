package com.example.aliuserdemo.service.serviceImpl;

import com.example.aliuserdemo.feign.FeignDemo;
import com.example.aliuserdemo.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private FeignDemo feignDemo;

    @Override
    public String getDemoOrder(String orderId) {
        System.out.println("zzz");
        return feignDemo.getDemoOrder(orderId);
    }
}
