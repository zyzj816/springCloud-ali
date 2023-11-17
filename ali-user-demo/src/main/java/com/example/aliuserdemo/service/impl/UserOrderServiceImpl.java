package com.example.aliuserdemo.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.aliuserdemo.feign.FeignDemo;
import com.example.aliuserdemo.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private FeignDemo feignDemo;

    //,fallback = "getDemoOrderFallBack",fallbackClass = UserOrderServiceImpl.class 容错
    @Override
    @SentinelResource(value = "getOrderNoResource",blockHandler = "getOrderNoBlockHandler",blockHandlerClass = UserOrderServiceImpl.class)
    public String getDemoOrder(String orderId, String name, ServerHttpRequest request) {
        System.out.println(orderId+"-"+name+"-"+request.getQueryParams().getFirst("token"));
        if(!"".equals(orderId)){
            String token = request.getQueryParams().getFirst("token");
            CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
                return feignDemo.getDemoOrder(orderId,name,token);
            });
            String o=null;
            try {
                o = completableFuture.get();
            }catch (Exception e){
                System.out.println(e);
            }
            return o;
        }else{
            throw new RuntimeException("订单号不存在");
        }
    }
    /**
     * 限流后续操作方法
     * @param e
     * @return
     */
    public static String getOrderNoBlockHandler(String userId, String name, ServerHttpRequest request, BlockException e){
        String msg = "不好意思，前方拥挤，请您稍后再试";
        return msg;
    }
    /**
     * 容错后续操作方法
     * @param
     * @return
     */
    public String getDemoOrderFallBack(String orderId, String name, String token) {
        System.out.println("进入容错");
        return "当前访问人数过多,请稍后再试!";
    }
}
