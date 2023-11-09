package com.example.aliuserdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="ali-demo-order")
@Component
public interface FeignDemo {

    @GetMapping("order/getDemoOrder")
    String getDemoOrder(@RequestParam String orderId);

}
