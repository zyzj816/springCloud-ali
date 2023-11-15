package com.example.aliuserdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="ali-demo-order")
@Component
public interface FeignDemo {


    /*@RequestParam: 标识单个参数
    @RequestHeader: 请求头参数
    @RequestHeader MultiValueMap<String, String> headers 多个参数
    * */
    @GetMapping("order/getDemoOrder")
    String getDemoOrder(@RequestParam String orderId, @RequestParam String name, @RequestHeader("token") String token);

}
