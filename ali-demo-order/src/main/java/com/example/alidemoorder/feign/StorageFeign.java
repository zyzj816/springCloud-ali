package com.example.alidemoorder.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ali-demo-storage")
@Component
public interface StorageFeign {

    /**
     * 扣减库存
     * @param commodityCode
     * @param count
     * @return
     */
    @GetMapping("storageTbl/deduct")
    String deduct(@RequestParam String commodityCode,@RequestParam Integer count);

}
