package com.example.alidemoorder.service.impl;

import com.example.alidemoorder.dto.OrderTblDao;
import com.example.alidemoorder.feign.StorageFeign;
import com.example.alidemoorder.pojo.OrderTblPojo;
import com.example.alidemoorder.service.OrderTblService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderTblServiceImpl implements OrderTblService {

    @Autowired
    private OrderTblDao orderTblDao;

    @Autowired
    private StorageFeign storageFeign;


    /**
     * 下单接口
     * @param userId 用户id
     * @param commodityCode 商品代码
     * @return
     */
    @Override
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(Long userId, String commodityCode) {
        try {
            System.out.println("事务id---------------------->" + RootContext.getXID());
            // 创建订单
            OrderTblPojo orderTblPojoBuilder = OrderTblPojo.builder()
                    .userId(userId)
                    .commodityCode(commodityCode)
                    .money(new BigDecimal(10))
                    .count(1)
                    .build();
            // 保存订单
            orderTblDao.insert(orderTblPojoBuilder);
            System.out.println(1/0);
            CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
                 return storageFeign.deduct(commodityCode, orderTblPojoBuilder.getCount());

            });
            // 保存订单成功后扣减库存
            System.out.println(completableFuture.get());
            return "success";
        }catch (Exception e){
            throw new RuntimeException("创建订单失败");
        }

    }


}
